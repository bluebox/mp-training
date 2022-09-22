from django.shortcuts import render, HttpResponse, redirect
from .models import adminusers, students, courses, subject, faculties, assign_subject, enter_marks
from .forms import studentsform, courseform, facultiesform, assign_subjectform, subjectform,adminusersform
# Create your views here.



def checkloginauth(request):
    if 'login_id' in request.COOKIES and 'user_name' in request.COOKIES:
        return True
    else:
        return False


def homepage(request):
    return render(request, "useradmin/login.html")


def logincompulsory(request):
    if checkloginauth(request):
        return redirect(logincompulsory)
    return render(request, "useradmin/login.html", {"text": "First login with valid username and password"})


def logincheck(request):
    username = request.POST['username']
    password = request.POST['password']
    try:
        users = adminusers.objects.get(username=username, password=password)
    except:
        users = False
    if users:
        response = redirect(adminpage)
        response.set_cookie('login_id', users.id)
        response.set_cookie('user_name', users.first_name)
        return response
    else:
        return render(request, "useradmin/login.html", {"text": "Invalid Id or Password"})


def adminpage(request):

    if checkloginauth(request) == False:
        return redirect(logincompulsory)
    if 'user_name' in request.COOKIES:
        user_name = request.COOKIES['user_name']

    student_obj = students.objects.all()
    student_num = student_obj.count()
    student_male = student_obj.filter(gender='male').count()
    student_female = student_obj.filter(gender='female').count()
    courses_num = courses.objects.all().count()
    subject_num = subject.objects.all().count()
    faculties_obj = faculties.objects.all()
    faculties_num = faculties_obj.count()
    fac_male = faculties_obj.filter(gender='male').count()
    fac_female = faculties_obj.filter(gender='female').count()
    count_data = {
        "student": student_num,
        "courses": courses_num,
        "subjects": subject_num,
        "faculties": faculties_num,
        "user_name": user_name,
        "student_male": student_male,
        "student_female": student_female,
        "fac_male": fac_male,
        "fac_female": fac_female
    }
    return render(request, "useradmin/adminhomepage.html", count_data)


def coursetable(request,id=0,de="false"):

    if checkloginauth(request) == False:
        return redirect(logincompulsory)
    if 'user_name' in request.COOKIES:
        user_name = request.COOKIES['user_name']
    total_courses = courses.objects.all()
    if id != 0:
        total_course = courses.objects.get(id=id)
        if de=="delete":
            total_course.delete()
            return redirect(coursetable)
        form_obj = courseform(instance=total_course)
        flag_open = True
    else:
        form_obj = courseform()
        flag_open = False
    dis_text = ""
    if request.method == "GET":
        try:
            flag = request.GET['sem']
        except:
            flag = False

        if flag:
            course_code=request.GET['courseid']
            if len(course_code)>1:
                total_courses=total_courses.filter(course_code=course_code)
            elif flag != 'nill':
                total_sem = request.GET["sem"]
                total_courses = total_courses.filter(total_sem=total_sem)

    if request.method == "POST":
        if id != 0:
            form_obj_post=courseform(request.POST,instance=total_course)
            form_obj_post.save()
            return redirect(coursetable)
        else:
            form_obj_post = courseform(request.POST)
            form_obj_post.save()

    data_table = {
        "data_heading": ["Course code", "Course Name", "Total Sem"],
        "total_course": total_courses,
        "form": form_obj,
        "user_name":user_name,
        "dis_text": dis_text,
        "form": form_obj,
        "flag_open": flag_open,
        'fid': id
    }
    return render(request, "useradmin/data_table/courses.html", data_table)


def studenttable(request,id=0,de="false"):
    if checkloginauth(request) == False:
        return redirect(logincompulsory)
    if 'user_name' in request.COOKIES:
        user_name = request.COOKIES['user_name']
    courses_dynamic_list = courses.objects.all()
    total_courses = courses.objects.all()
    total_student = students.objects.all()
    if id != 0:
        student_data = students.objects.get(id=id)
        if de=="delete":
            student_data.delete()
            return redirect(studenttable)
        form_obj = studentsform(instance=student_data)
        flag_open = True
    else:
        form_obj = studentsform()
        flag_open = False
    
    dis_text = ""
    if request.method == 'GET':
        try:
            flag = request.GET['sem']
        except:
            flag = False

        if flag:
            std_code=request.GET['stdcode']
            if len(std_code)>1:
                total_student=total_student.filter(admission_id=std_code)
            elif request.GET['sem'] != 'nill' and request.GET['course'] == 'nill':
                total_student = total_student.filter(
                    semester=request.GET['sem'])
            elif request.GET['sem'] != 'nill' and request.GET['course'] != 'nill':
                total_student = total_student.filter(
                    semester=request.GET['sem'], course=request.GET['course'])
            elif request.GET['sem'] == 'nill' and request.GET['course'] != 'nill':
                total_student = total_student.filter(
                    course=request.GET['course'])
            elif request.GET['sem'] == 'nill' and request.GET['course'] == 'nill':
                pass
        else:
            pass

    if request.method == "POST":
        if id != 0:
            form_obj_post = studentsform(request.POST, instance=student_data)
            form_obj_post.save()
            return redirect(studenttable)
        else:
            form_obj_post = studentsform(request.POST)
            form_obj_post.save()
        dis_text = "student added successfully!!"
    print(total_student)
    data_table = {
        "data_heading": ["admission_id","Full Name", "DOB", "username", "course", "semester", "email", "phone", "father_name"],
        "total_student": total_student,
        "dis_text": dis_text,
        "total_course": total_courses,
        "user_name":user_name,
        "form": form_obj,
        "flag_open": flag_open,
        'fid': id,
                "courses_dynamic_list":courses_dynamic_list
    }
    return render(request, "useradmin/data_table/students.html", data_table)


def subjecttable(request, id=0,de="false"):
    if checkloginauth(request) == False:
        return redirect(logincompulsory)
    courses_dynamic_list = courses.objects.all()
    if 'user_name' in request.COOKIES:
        user_name = request.COOKIES['user_name']
    total_subject = subject.objects.all()
    total_courses = courses.objects.all()
    if id != 0:
        subject_data = subject.objects.get(id=id)
        if de=="delete":
            subject_data.delete()
            return redirect(subjecttable)
            
        form_obj = subjectform(instance=subject_data)
        flag_open = True
    else:
        form_obj = subjectform()
        flag_open = False

    dis_text = ""
    if request.method == 'GET':
        try:
            flag = request.GET['sem']
        except:
            flag = False

        if flag:                
            if request.method == "GET":
                sub_code=request.GET['subcode']
                course_get = request.GET["course"]
                sem_get = request.GET["sem"]
            if len(sub_code)>1:
                total_subject=total_subject.filter(sub_code=sub_code)
            elif sem_get != 'nill' and course_get != 'nill':
                total_subject = total_subject.filter(
                    course=course_get, semester=sem_get)
            elif sem_get != 'nill' and course_get == 'nill':
                total_subject = total_subject.filter(semester=sem_get)
            elif sem_get == 'nill' and course_get != 'nill':
                total_subject = total_subject.filter(course=course_get)

    if request.method == "POST":
        if id != 0:
            form_obj_post = subjectform(request.POST, instance=subject_data)
            form_obj_post.save()
            return redirect(subjecttable)
        else:
            form_obj_post = subjectform(request.POST)
            form_obj_post.save()
        dis_text = "Subject added successfully!!"

    data_table = {
        "data_heading": ["sub_code", "sub_name", "course", "semester", "theory_marks"],
        "total_course": total_subject,
        "total_courses": total_courses,
        "dis_text": dis_text,
        "user_name":user_name,
        "form": form_obj,
        "flag_open": flag_open,
        'fid': id,
        "courses_dynamic_list":courses_dynamic_list
    }
    return render(request, "useradmin/data_table/subject.html", data_table)


def facultytable(request,id=0,de="false"):
    if checkloginauth(request) == False:
        return redirect(logincompulsory)
    if 'user_name' in request.COOKIES:
        user_name = request.COOKIES['user_name']
    total_faculty = faculties.objects.all()
    if request.method == 'GET':
        try:
            flag = request.GET['experience']
        except:
            flag = False

        if flag:
            if request.method == "GET":
                fac_id=request.GET['facid']
                total_experience = int(request.GET["experience"])
                if len(fac_id)>1:
                    total_faculty=total_faculty.filter(fac_id=fac_id)
                elif total_experience == 1:
                    total_faculty = total_faculty.filter(
                        experience__lt=total_experience)
                elif total_experience == 0:
                    pass
                elif total_experience == 9:
                    total_faculty = total_faculty.filter(
                        experience__gte=total_experience)
                else:
                    total_faculty = total_faculty.filter(
                        experience__gte=total_experience-1, experience__lte=total_experience)


    if id != 0:
        fac_data = faculties.objects.get(id=id)
        if de=="delete":
            fac_data.delete()
            return redirect(facultytable)
        form_obj = facultiesform(instance=fac_data)
        flag_open = True
    else:
        form_obj = facultiesform()
        flag_open = False
    if request.method == 'POST':
        if id != 0:
            form_obj_post = facultiesform(request.POST, instance=fac_data)
            form_obj_post.save()
            return redirect(facultytable)
        else:
            form_obj_post = facultiesform(request.POST)
            form_obj_post.save()
        dis_text = "Subject added successfully!!"
    data_table = {
        "data_heading": ["fac_id", "first_name", "last_name", "faculties_dob", "qualifications", "experience", "username"],
        "total_course": total_faculty,
        'form': form_obj,
        "user_name":user_name,
        "flag_open": flag_open,
        'fid': id

    }
    print(total_faculty)
    return render(request, "useradmin/data_table/faculties.html", data_table)


def assignsubjects(request,id=0,de="false"):
    if checkloginauth(request) == False:
        return redirect(logincompulsory)
    courses_dynamic_list = courses.objects.all()
    if 'user_name' in request.COOKIES:
        user_name = request.COOKIES['user_name']
        
    flag_open=False
    total_assign = assign_subject.objects.all().prefetch_related('sub_code')

    if id != 0:
        total_assign_id = assign_subject.objects.get(id=id)
        if de=="delete":
            total_assign_id.delete()
            return redirect(assignsubjects)
        form_obj = assign_subjectform(instance=total_assign_id)
        flag_open = True
    else:
        form_obj = assign_subjectform()
        flag_open = False
    
    if request.method == 'POST':
        if id != 0:
            form_obj_post = assign_subjectform(request.POST, instance=total_assign_id)
            form_obj_post.save()
            return redirect(assignsubjects)
        else:
            form_obj_post = assign_subjectform(request.POST)
            form_obj_post.save()
        dis_text = "Subject added successfully!!"
    data_table = {
        "data_heading": ["fac_id", "fac_name", "sub_code", "sub_name", "semester", "course"],
        "total_course": total_assign,
        "form": form_obj,
        "flag_open": flag_open,
        "user_name":user_name,
        "courses_dynamic_list":courses_dynamic_list
    }
    if request.method == 'GET':
        try:
            flag = request.GET['sem']
        except:
            flag = False

        if flag:
            if request.method == "GET":
                sub_code=request.GET['subcode']
                course_get = request.GET["course"]
                sem_get = request.GET["sem"]
                if len(sub_code)>1:
                    sub=subject.objects.get(sub_code=sub_code)
                    total_assign=total_assign.filter(sub_code=sub)
                    data_table = {
                        "data_heading": ["fac_id", "fac_name", "sub_code", "sub_name", "semester", "course"],
                        "total_course": total_assign,
                        "form": form_obj,
                        "user_name":user_name,
                        "flag_open": flag_open,
                                "courses_dynamic_list":courses_dynamic_list
                    }
                elif course_get != 'nill' and sem_get != 'nill':
                    data_table = {
                        "data_heading": ["fac_id", "fac_name", "sub_code", "sub_name", "semester", "course"],
                        "total_course": total_assign,
                        "form": form_obj,
                        'course_get': course_get,
                        'sem_get': sem_get,
                        "flag_open": flag_open,
                        "user_name":user_name,
                                "courses_dynamic_list":courses_dynamic_list
                    }
                if course_get != 'nill' and sem_get == 'nill':
                    data_table = {
                        "data_heading": ["fac_id", "fac_name", "sub_code", "sub_name", "semester", "course"],
                        "total_course": total_assign,
                        "form": form_obj,
                        'course_get': course_get,
                        "flag_open": flag_open,
                        "user_name":user_name,
                                "courses_dynamic_list":courses_dynamic_list
                    }

    return render(request, "useradmin/data_table/assign_sub.html", data_table)


def entermarks(request):
    if checkloginauth(request) == False:
        return redirect(logincompulsory)
    courses_dynamic_list = courses.objects.all()
    if 'user_name' in request.COOKIES:
        user_name = request.COOKIES['user_name']
    if request.method == "POST":
        if request.POST['sem'] == 'nill' or request.POST['course'] == 'nill':
            return HttpResponse("please enter both semester and course")
        else:
            sem = request.POST['sem']
            course = request.POST['course']
            total_subjects = subject.objects.all().filter(course=course, semester=sem)
            total_student = students.objects.all().filter(semester=sem, course=course)
            data_table = {
                "data_heading": ["student_name", "Roll_number"],
                "sem": sem,
                "course": course,
                "total_subs": total_subjects,
                "total_students": total_student,
                "user_name":user_name,
                        "courses_dynamic_list":courses_dynamic_list
            }
        return render(request, "useradmin/data_table/enter_marks.html", data_table)
    return render(request, "useradmin/data_table/enter_marks.html", {"sem": 'nill', "course": 'nill',"user_name":user_name,"courses_dynamic_list":courses_dynamic_list })


def markssubmited(request, sem, course):
    if checkloginauth(request) == False:
        return redirect(logincompulsory)

    total_subjects = subject.objects.filter(course=course, semester=sem)
    total_student = students.objects.filter(semester=sem, course=course)
    for i in total_student:
        for j in total_subjects:
            print(request.POST[str(i.roll_number)+j.sub_name])
            obj = enter_marks()
            obj.std_id = i
            obj.sub_id = j
            obj.marks = request.POST[str(i.roll_number)+j.sub_name]
            obj.save()
    return HttpResponse("marks submited successfully")


def viewmarksheet(request):
    if checkloginauth(request) == False:
        return redirect(logincompulsory)
    courses_dynamic_list = courses.objects.all()
    if 'user_name' in request.COOKIES:
        user_name = request.COOKIES['user_name']
    if request.method == "POST":
        if request.POST['sem'] == 'nill' or request.POST['course'] == 'nill':
            return HttpResponse("please enter both semester and course")
        else:
            main_dict={}
            temp_dict={}
            marks_data = enter_marks.objects.all().prefetch_related('sub_id')
            count_subs= subject.objects.filter(semester=request.POST['sem'] , course=request.POST['course']).count()
            # std_count=students.objects.filter(semester=request.POST['sem'] , course=request.POST['course']).count()
            print(count_subs)
            flag=False
            counter=0
            main_counter=0
            for i in marks_data:
                if flag==False:
                    name=(i.std_id.first_name+" "+i.std_id.last_name)
                    flag=True
                temp_dict[i.sub_id.sub_name]=i.marks
                counter=counter+1

                if counter==count_subs:
                    main_dict[name]=dict(temp_dict)
                    # print(main_dict)
                    counter=0
                    flag=False
                    temp_dict.clear()
                # if main_counter==std_count*count_subs:
                
 

            print(main_dict)
            data_table = {
                "data_heading": ["student_name", "Roll_number"],
                # 'marks_data':marks_data
                'marks_data':main_dict,
                "user_name":user_name,
                        "courses_dynamic_list":courses_dynamic_list
            }

            return render(request, "useradmin/data_table/marksheet_report.html", data_table)
    return render(request, "useradmin/data_table/marksheet_report.html",{"user_name":user_name,        "courses_dynamic_list":courses_dynamic_list})


def aboutadmin(request):
    if checkloginauth(request) == False:
        return redirect(logincompulsory)
    if 'login_id' in request.COOKIES:
        user_id = request.COOKIES['login_id']
        user_name = request.COOKIES['user_name']
    user_details = adminusers.objects.get(id=user_id)
    form_obj=adminusersform(instance=user_details)
    if request.method=="POST":
        form_obj=adminusersform(request.POST,instance=user_details)
        form_obj.save()
    data_table = {
        "data_heading": ["First_name", "last_name", "DOB", "username", "password"],
        'user_data': user_details,
        'user_name':user_name,
        "form":form_obj
    }
    return render(request, "useradmin/aboutpage.html", data_table)


def logout(request):
    response = render(request, "useradmin/login.html", {"text_logout": "Logout successfully"})
    if 'login_id' in request.COOKIES and 'user_name' in request.COOKIES:
        response.delete_cookie('login_id')
        response.delete_cookie('user_name')
        return response
