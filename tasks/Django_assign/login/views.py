from django.urls import reverse
from .forms import Login
from django.shortcuts import render
import mysql.connector as sql
from django.template import loader
from django.http import HttpResponse, HttpResponseRedirect
from .filters import SomeFilter
from .models import StudentsList, StudentPassword


user = ''
pwd = ''
table = ''


def loginaction(request):
    global table, user, pwd
    if request.method == "POST":
        if len(request.POST.get('Username')) < 4:
            return render(request, 'login.html', {'error': True})
        else:
            d = request.POST
            # details = Login(d)
            # if details.is_valid():
            #     return HttpResponse(d['Username'])
            m = sql.connect(host='localhost', user='root', password='Test@123', database='Students_db')
            cursor = m.cursor()
            # d = request.POST
            for key, value in d.items():
                if key == "member":
                    table = value
                if key == "Username":
                    user = value
                if key == "Password":
                    pwd = value
            c = f"Select * from {table} where username='{user}' and password='{pwd}'"
            cursor.execute(c)
            t = tuple(cursor.fetchall())

            if t == ():
                return render(request, "error.html")

            elif table == "student_password":
                return student_login(request)

            else:
                return faculty_login(request)
                # return faculty(request)
            # else:
            #     return render(request, "login.html", {'form': details})
    else:
        return render(request, "login.html")


def student_login(request):
    global user
    m = sql.connect(host='localhost', user='root', password='Test@123', database='Students_db')
    cursor = m.cursor()
    log_data = f"select * from students_list where Rollno = '{user}'"
    cursor.execute(log_data)
    sqldata = tuple(cursor.fetchall())
    context = {
        'user': sqldata,
    }
    return render(request, "student_login.html", context)


def faculty_login(request):
    mymembers = StudentsList.objects.all()
    myfilter = SomeFilter(request.GET, queryset=mymembers)
    template = loader.get_template('faculty.html')
    mymembers = myfilter.qs
    context = {
        'myfilter': myfilter,
        'user': mymembers,
    }
    return HttpResponse(template.render(context, request))

# def faculty(request):
#     mymembers = StudentsList.objects.all()
#     myfilter = SomeFilter(request.GET, queryset=mymembers)
#     template = loader.get_template('faculty.html')
#     mymembers = myfilter.qs
#     context = {
#         'myfilter': myfilter,
#         'user': mymembers,
#     }
#     return HttpResponse(template.render(context, request))


def addaction(request):
    return render(request, 'add.html')


def addrecord(request):
    if request.method == "POST":
        if len(request.POST.get('Student_name')) < 4:
            return render(request, 'add.html', {'error': True})
        if len(request.POST.get('Rollno')) < 4:
            return render(request, 'add.html', {'error1': True})
        else:
            roll = nam = dep = ''
            s1 = s2 = s3 = s4 = s5 = s6 = s7 = s8 = s9 = s10 = s11 = s12 =0
            m = sql.connect(host='localhost', user='root', password='Test@123', database='Students_db')
            cursor = m.cursor()
            d = request.POST
            for key, value in d.items():
                if key == "Rollno":
                    roll = value
                if key == "Student_name":
                    nam = value
                if key == "Dept":
                    dep = value
                if key == "S1":
                    s1 = value
                if key == "S2":
                    s2 = value
                if key == "S3":
                    s3 = value
                if key == "S4":
                    s4 = value
                if key == "S5":
                    s5 = value
                if key == "S6":
                    s6 = value
                if key == "S7":
                    s7 = value
                if key == "S8":
                    s8 = value
                if key == "S9":
                    s9 = value
                if key == "S10":
                    s10 = value
                if key == "S11":
                    s11 = value
                if key == "S12":
                    s12 = value

            # inserting data into students_list table
            c = f'''Insert into students_list values('{roll}','{nam}','{dep}',{s1},{s2},{s3},{s4},{s5},{s6},
            {s7},{s8},{s9},{s10},{s11},{s12})'''
            cursor.execute(c)
            m.commit()

            # inserting data into student_password table
            c = f"Insert into student_password values('{roll}','12345')"
            cursor.execute(c)
            m.commit()

            # fetching updated data from students_list as context for returning
            mymembers = StudentsList.objects.all()
            context = {
                'user': mymembers,
            }
            return HttpResponseRedirect(reverse('facultylogin'))
    return HttpResponse('Something went wrong')


def update(request, id):
    m = sql.connect(host='localhost', user='root', password='Test@123', database='Students_db')
    cursor = m.cursor()
    list_data = f"Select * from students_list where Rollno = '{id}'"
    cursor.execute(list_data)
    sqldata = tuple(cursor.fetchall())
    context = {
        'user': sqldata,
    }
    return render(request, "update.html", context)


def updaterecord(request):
    if request.method == "POST":
        roll = nam = dep = ''
        s1 = s2 = s3 = s4 = s5 = s6 = s7 = s8 = s9 = s10 = s11 = s12 = 0
        m = sql.connect(host='localhost', user='root', password='Test@123', database='Students_db')
        cursor = m.cursor()
        d = request.POST
        for key, value in d.items():
            if key == "Rollno":
                roll = value
            if key == "Student_name":
                nam = value
            if key == "S1":
                s1 = value
            if key == "S2":
                s2 = value
            if key == "S3":
                s3 = value
            if key == "S4":
                s4 = value
            if key == "S5":
                s5 = value
            if key == "S6":
                s6 = value
            if key == "S7":
                s7 = value
            if key == "S8":
                s8 = value
            if key == "S9":
                s9 = value
            if key == "S10":
                s10 = value
            if key == "S11":
                s11 = value
            if key == "S12":
                s12 = value

        c = f'''Update students_list set Student_name='{nam}',Sub1={s1},Sub2={s2},Sub3={s3},Sub4={s4},Sub5={s5},
                Sub6={s6},Sub7={s7},Sub8={s8},Sub9={s9},Sub10={s10},Sub11={s11},Sub12={s12} where Rollno="{roll}"'''
        cursor.execute(c)
        m.commit()

        mymembers = StudentsList.objects.all()
        context = {
            'user': mymembers,
        }
        return HttpResponseRedirect(reverse('facultylogin'))
    return HttpResponse('Something went wrong')


def delete(request, id):
    m = sql.connect(host='localhost', user='root', password='Test@123', database='Students_db')
    cursor = m.cursor()
    # del from student_password & students_list tables
    query = f'''Delete from student_password where Username="{id}"'''
    cursor.execute(query)
    m.commit()
    query = f'''Delete from students_list where Rollno="{id}"'''
    cursor.execute(query)
    m.commit()
    # return all the data to html page after deleting
    mymembers = StudentsList.objects.all()
    context = {
        'user': mymembers,
    }
    return faculty_login(request)


# m = sql.connect(host='localhost', user='root', password='Test@123', database='Students_db')
# cursor = m.cursor()
# c = "Select * from student_password where username='ME001' and password='12345'"
# cursor.execute(c)
# t = tuple(cursor.fetchall())
# print(t)
#
# log_data = "select * from students_list where Rollno = 'ME001'"
# cursor.execute(log_data)
# sqldata = tuple(cursor.fetchall())
# print(sqldata)

#
#
#
#
#
