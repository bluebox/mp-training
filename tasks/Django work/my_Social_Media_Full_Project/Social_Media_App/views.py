# from django.shortcuts import redirect, render
# from django.http import HttpResponse
# from .models import likepost, posting, profile, followerscount
# from django.contrib.auth.decorators import login_required
# from django.contrib.auth.models import User,auth
# from django.contrib import messages
# from itertools import chain
# import random
# @login_required(login_url='signin')
# def home(request):
#     user_object = User.objects.get(username = request.user.username)
#     # print(user_object.first_name)
#     user_profile = profile.objects.get(user = user_object)


#     user_following_list = []
#     feed = []
#     user_following = followerscount.objects.filter(follower =request.user.username)
#     for users in user_following:
#         user_following_list.append(users.user)


#     for usernames  in user_following_list:
#         feed_list = posting.objects.filter(user = usernames)
#         feed.append(feed_list)


#     feed_list = list(chain(*feed))

#     # user suggestions 
#     all_users = User.objects.all()
#     user_following_all = []

#     for user in user_following:
#         user_list = User.objects.get(username = user.user)
#         user_following_all.append(user_list)


#     new_suggest_list = [x for x in list(all_users) if (x not in list(user_following_all))]
#     curr_user = User.objects.filter(username = request.user.username)
#     final_suggest_list = [x for x in list(new_suggest_list) if (x not in list(curr_user))]
#     random.shuffle(final_suggest_list)
#     username_profile = []
#     username_profile_list = []
#     for users in final_suggest_list:
#         username_profile.append(users.id)
#     for ids in username_profile:
#         profile_list = profile.objects.filter(id_user= ids)
#         username_profile_list.append(profile_list)

#     suggestions_usernmae_profile_list = list(chain(*username_profile_list))        

#     # print(user_profile.bio)
#     # posts = posting.objects.all()
#     return render(request,"index.html", {'user_profile': user_profile ,  "posts":feed_list, "suggestions":suggestions_usernmae_profile_list[:4]})

# def signup(request):
#     if request.method == 'POST':
#         username = request.POST['username']
#         firstname = request.POST['fname']
#         lastname = request.POST['lname']
#         email = request.POST['email']
#         password1 = request.POST['pass1']
#         password2 = request.POST['pass2']
#         if len(username) == 0:
#             messages.info(request,"Username is required,")
#         elif len(firstname) == 0:
#             messages.info(request,"firstname is required,")

#         elif len(lastname) == 0:
#             messages.info(request,"lastname is required,")
#         elif len(email) == 0:
#             messages.info(request,"email is required,")
#         elif len(password1) == 0:
#             messages.info(request,"password is required,please enter password")
#         elif len(password2) == 0:
#             messages.info(request,"Confirmed password is required to confirm")

#         if password1 == password2:
#             if User.objects.filter(username=username).exists():
#                 messages.info(request, 'Username Already Exists, Please Use another one')
#                 return redirect('signup')
#             elif User.objects.filter(email=email).exists():
#                 messages.info(request, 'Email Already Exists, Please use Another Email Addresss')
#                 return redirect('signup')
#             else:
#                 user = User.objects.create_user(username=username,first_name = firstname,last_name=lastname, email=email, password=password1 )
#                 user.save()

#                 #log user in and redirect to settings page
#                 userlogin = auth.authenticate(username=username, password=password1)
#                 auth.login(request, userlogin)


#                 # #create a Profile object for the new user
#                 user_model = User.objects.get(username=username)
#                 new_profile = profile.objects.create(user=user_model, id_user=user_model.id)
#                 new_profile.save()
#                 return redirect('settings')
#         else:
#             messages.info(request, 'Password Not Matching, please enter correct password')
#             return redirect('signup')

#     else:
#         return render(request, 'signup.html')


# def signin(request):
#     if request.method == 'POST':
#         username = request.POST['username']
#         password = request.POST['password']

#         user = auth.authenticate(username=username, password=password)
#         if user is not None:
#             auth.login(request, user)
#             return redirect('/')

#         else:
#             messages.info(request,"Username or Password is Invalid")  
#             return redirect('signin')  
#     else:
#         return render(request, "signin.html")


# @login_required(login_url='signin')
# def settings(request):
#     user_profile = profile.objects.get(user=request.user)
#     if request.method == 'POST':

#         if request.FILES.get('image') == None:
#             image = user_profile.profile_img
#             bio = request.POST['bio']
#             location = request.POST['address']

#             user_profile.profile_img = image
#             user_profile.bio = bio
#             user_profile.location = location
#             user_profile.save()

#         if request.FILES.get('image') != None:    
#             image = request.FILES.get('image')
#             bio = request.POST['bio']
#             location = request.POST['address']   
#             user_profile.profile_img = image
#             user_profile.bio = bio
#             user_profile.location = location
#             user_profile.save()

#         return redirect('settings')


#     return render(request, "setting.html" ,{"user_profile":user_profile})

# def example(request):
#     return render(request, "signin.html") 

# @login_required(login_url='signin')
# def upload_post(request):
#     if request.method =='POST':
#         user = request.user.username
#         image = request.FILES.get('image_upload')
#         caption = request.POST['caption']
#         print(caption)

#         post1 = posting.objects.create(user=user, image=image, caption=caption)
#         post1.save()

#         return redirect('/')
#     else:
#         return redirect('/')

# @login_required(login_url='signin')     
# def like_post(request):
#     username = request.user.username
#     post_id = request.GET.get('post_id')
#     post = posting.objects.get(id = post_id)
#     likefilter = likepost.objects.filter(post_id=post_id, username=username).first()
#     if likefilter == None:
#         new_like = likepost.objects.create(post_id=post_id, username=username)
#         new_like.save()
#         post.likes = post.likes + 1
#         post.save()
#         return redirect('/')
#     else:
#         likefilter.delete()
#         post.likes = post.likes - 1
#         post.save()
#         return redirect('/')

# @login_required(login_url='signin')
# def Profile(request, pk):
#     user_object = User.objects.get(username=pk)
#     print(user_object.username)
#     user_profile = profile.objects.get(user = user_object)
#     user_posts = posting.objects.filter(user = pk)
#     user_posts_len = len(user_posts)
#     follower = request.user.username
#     user = pk
#     if followerscount.objects.filter(follower=follower, user=user).first():
#         butt_text = 'Unfollow'

#     else:
#         butt_text ="Follow"   

#     user_followers =len(followerscount.objects.filter(user=pk))
#     user_following =len(followerscount.objects.filter(follower=pk))
#     context ={
#         'user_objects':user_object,
#         'user_profile':user_profile,
#         'user_posts_len':user_posts_len,
#         'user_posts':user_posts,
#         'button_text':butt_text,
#         "user_followers":user_followers,
#         "user_following":user_following,
#         }
#     return render(request,"profile.html", context)


# @login_required(login_url='signin')
# def signout(request):
#     auth.logout(request)
#     return redirect('signin')


# @login_required(login_url='signin')
# def follow(request):
#     if request.method == 'POST':
#        follower = request.POST['follower']
#        user = request.POST['user']


#        if followerscount.objects.filter(follower=follower, user=user).first():
#            delete_follower = followerscount.objects.get(follower =follower, user=user)
#            delete_follower.delete()
#            return redirect('/profile/'+user)
#        else:
#             new_follower = followerscount.objects.create(follower =follower,user=user)
#             new_follower.save()
#             return redirect('/profile/'+user)
#     else:
#         return redirect('/')

# @login_required(login_url='signin')
# def search(request):
#     user_object = User.objects.get(username = request.user.username)
#     user_profile = profile.objects.get(user = user_object)
#     if request.method == 'POST':
#         username = request.POST['username']
#         username_obj = User.objects.filter(username__icontains = username)

#         username_profile=[]
#         username_profile_list = []
#         for users in username_obj:
#             username_profile.append(users.id)

#         for ids in username_profile:
#             profile_list= profile.objects.filter(id_user=ids)
#             username_profile_list.append(profile_list)
#         username_profile_list = list(chain(*username_profile_list))
#     return render(request, 'searching.html',{"user_profile":user_profile,"username_list":username_profile_list})


# # @login_required(login_url='signin')
# # def follow(request):
# #     if request.method == 'POST':
# #         follower = request.POST['follower']
# #         user = request.POST['user']

# #         if followerscount.objects.filter(follower=follower, user=user).first():
# #             delete_follower = followerscount.objects.get(follower=follower, user=user)
# #             delete_follower.delete()
# #             return redirect('/profile/'+user)
# #         else:
# #             new_follower = followerscount.objects.create(follower=follower, user=user)
# #             new_follower.save()
# #             return redirect('/profile/'+user)
# #     else:
# #         return redirect('/')

from base64 import urlsafe_b64decode
from email import message
from email.headerregistry import Group
import imp
from lib2to3.pgen2.tokenize import generate_tokens
from django.shortcuts import render, redirect
from django.contrib.auth.models import User, auth
from django.contrib.sites.shortcuts import get_current_site
from django.template.loader import render_to_string
from django.utils.http import urlsafe_base64_encode, urlsafe_base64_decode
from django.utils.encoding import force_bytes, force_str
from django.contrib import messages
from django.http import HttpResponse
from django.contrib.auth.decorators import login_required
from .models import Profile, posting, likepost, followerscount,Groups,Message
from itertools import chain
import random
from django.core.mail import EmailMessage,send_mail 
from django.core.mail import send_mail
from .tokens import generate_token
from Social_Media_Project.settings import *
from messageapp.models import ThreadingTable, Messages1



# Create your views here.

@login_required(login_url='signin')
def home(request):
    user_object = User.objects.get(username=request.user.username)
    user_profile = Profile.objects.get(user=user_object)

    user_following_list = []
    feed = []

    user_following = followerscount.objects.filter(follower=request.user.username)
    # for i in user_following:
    #     x=followerscount.objects.filter(follower=i.follower)
    #     print(x.first())
    #     if x.exists():
    #         if  followerscount.objects.get(follower=i.follower).user.exists():
    #             pass
            
    #     else:
    #         s=ThreadingTable(first_person=i.user,second_person=i.follower)
    #         s.save()
    threads = ThreadingTable.objects.by_user(user=request.user).prefetch_related('message1').order_by('timestamp')         
    for users in user_following:
        user_following_list.append(users.user)

    for usernames in user_following_list:
        feed_lists = posting.objects.filter(user=usernames)
        feed.append(feed_lists)

    feed_list = list(chain(*feed))

    # user suggestion starts
    all_users = User.objects.all()
    user_following_all = []

    for user in user_following:
        user_list = User.objects.get(username=user.user)
        user_following_all.append(user_list)

    new_suggestions_list = [x for x in list(all_users) if (x not in list(user_following_all))]
    current_user = User.objects.filter(username=request.user.username)
    final_suggestions_list = [x for x in list(new_suggestions_list) if (x not in list(current_user))]
    random.shuffle(final_suggestions_list)

    username_profile = []
    username_profile_list = []

    for users in final_suggestions_list:
        username_profile.append(users.id)

    for ids in username_profile:
        profile_lists = Profile.objects.filter(id_user=ids)
        username_profile_list.append(profile_lists)

    suggestions_username_profile_list = list(chain(*username_profile_list))

    return render(request, 'index.html', {'user_profile': user_profile, 'user_object': user_object, 'posts': feed_list,'threads':threads,
                                          'suggestions': suggestions_username_profile_list[:4]})


@login_required(login_url='signin')
def upload_post(request):
    if request.method == 'POST':
        user = request.user.username
        image = request.FILES.get('image_upload')
        caption = request.POST['caption']

        new_post = posting.objects.create(user=user, image=image, caption=caption)
        new_post.save()

        return redirect('/')
    else:
        return redirect('/')


@login_required(login_url='signin')
def search(request):
    user_object = User.objects.get(username=request.user.username)
    user_profile = Profile.objects.get(user=user_object)
    username=[]

    if request.method == 'POST':
        username = request.POST['username']
        print(username)
        username_object = User.objects.filter(username__icontains=username)

        username_profile = []
        username_profile_list = []

        for users in username_object:
            username_profile.append(users.id)

        for ids in username_profile:
            profile_lists = Profile.objects.filter(id_user=ids)
            username_profile_list.append(profile_lists)

        username_profile_list = list(chain(*username_profile_list))
    return render(request, 'searching.html', {'user_profile': user_profile, 'username_list': username_profile_list,"user_name":username})


@login_required(login_url='signin')
def like_post(request):
    username = request.user.username
    post_id = request.GET.get('post_id')

    post = posting.objects.get(id=post_id)

    like_filter = likepost.objects.filter(post_id=post_id, username=username).first()

    if like_filter == None:
        new_like = likepost.objects.create(post_id=post_id, username=username)
        new_like.save()
        post.likes = post.likes + 1
        post.save()
        return redirect('/')
    else:
        like_filter.delete()
        post.likes = post.likes - 1
        post.save()
        return redirect('/')


@login_required(login_url='signin')
def profile(request, pk):
    user_object = User.objects.get(username=pk)
    user_profile = Profile.objects.get(user=user_object)
    user_posts = posting.objects.filter(user=pk)
    user_post_length = len(user_posts)

    follower = request.user.username
    user = pk

    if followerscount.objects.filter(follower=follower, user=user).first():
        button_text = 'Unfollow'
    else:
        button_text = 'Follow'

    user_followers = len(followerscount.objects.filter(user=pk))
    user_following = len(followerscount.objects.filter(follower=pk))

    context = {
        'user_object': user_object,
        'user_profile': user_profile,
        'user_posts': user_posts,
        'user_posts_len': user_post_length,
        'button_text': button_text,
        'user_followers': user_followers,
        'user_following': user_following,
    }
    return render(request, 'profile.html', context)

@login_required(login_url='signin')
def follow(request):
    if request.method == 'POST':
        follower = request.POST['follower']
        user = request.POST['user']
        if followerscount.objects.filter(follower=follower, user=user).first():
            delete_follower = followerscount.objects.get(follower=follower, user=user)
            delete_follower.delete()
            return redirect('/profile/' + user)
        else:
            new_follower = followerscount.objects.create(follower=follower, user=user)
            new_follower.save()
            return redirect('/profile/' + user)
    else:
        return redirect('/')


@login_required(login_url='signin')
def settings(request):
    user_profile = Profile.objects.get(user=request.user)
    user_obect = User.objects.get(username = request.user.username)
    print(user_obect.first_name)

    if request.method == 'POST':

        if request.FILES.get('image') == None:
            image = user_profile.profile_img
            bio = request.POST['bio']
            location = request.POST['address']
            firstname = request.POST['fname']
            lastname = request.POST['lname']
            work = request.POST['working']
           
 
            user_profile.profile_img = image
            user_profile.bio = bio
            user_profile.location = location
            user_profile.working = work
            
            user_profile.save()
            user_obect.first_name=firstname
            user_obect.last_name=lastname
            user_obect.save()

        if request.FILES.get('image') != None:
            image = request.FILES.get('image')
            print(image)
            bio = request.POST['bio']
            print(bio)
            location = request.POST['address']

            user_profile.profile_img = image
            user_profile.bio = bio
            user_profile.location = location
            user_profile.save()

        return redirect('settings')
    return render(request, 'setting.html', {'user_profile': user_profile})


def signup(request):
    if request.method == 'POST':
        username = request.POST['username']
        firstname = request.POST['firstname']
        lastname = request.POST['lastname']
        email = request.POST['email']
        password1 = request.POST['pass1']
        password2 = request.POST['pass2']
        if len(username) == 0:
            messages.info(request, "Username is required,")
        elif len(firstname) == 0:
            messages.info(request, "firstname is required,")

        elif len(lastname) == 0:
            messages.info(request, "lastname is required,")
        elif len(email) == 0:
            messages.info(request, "email is required,")
        elif len(password1) == 0:
            messages.info(request, "password is required,please enter password")
        elif len(password2) == 0:
            messages.info(request, "Confirmed password is required to confirm")

        if password1 == password2:
            if User.objects.filter(username=username).exists():
                messages.info(request, 'Username Already Exists, Please Use another one')
                return redirect('signup')
            elif User.objects.filter(email=email).exists():
                messages.info(request, 'Email Already Exists, Please use Another Email Addresss')
                return redirect('signup')
            else:
                user = User.objects.create_user(username=username, first_name=firstname, last_name=lastname,
                                                email=email, password=password1)
                user.is_active=False                                
                user.save()
                messages.success(request, "your Acount has been succesfully created. We have sent  you a confirmation email, please confirm your email in order to activate your account.")
                # sending emails
                # welcome email
                subject = "welcome to my app Django-login"
                message = "hello" + user.first_name + "|| \n" + " Welcome to Website Login \n Thank you for visiting website \n we have also sent you a confirmation email, please confirm your email Address in order to activate your account.\n\n Thanking You \n Shyam Kumar"
                from_email = EMAIL_HOST_USER
                to_list = [user.email]
                send_mail(subject, message, from_email, to_list, fail_silently = True) 
            

                # email adress confirmation email
                current_site = get_current_site(request)
                
                email_subject = "confirm your email @ shyam website!!"
                message2 = render_to_string('email_confirmation.html',{
                    'name':user.first_name,
                    'domain':current_site.domain,
                    'uid': urlsafe_base64_encode(force_bytes(user.pk)),
                    'token': generate_token.make_token(user)

                }) 
                # create email object
                email = EmailMessage(
                    email_subject,
                    message2,
                    EMAIL_HOST_USER, 
                    [user.email],
                )
                email.fail_silently = True
                email.send() 

                return redirect('signin')
        else:
            messages.info(request, 'Password Not Matching')
            return redirect('signup')

    else:
        return render(request, 'signup.html')


def signin(request):
    if request.method == 'POST':
        username = request.POST['username']
        password = request.POST['password']

        user = auth.authenticate(username=username, password=password)

        if user is not None:
            auth.login(request, user)
            
           

            # #create a Profile object for the new user
            

            return redirect('/')
        else:
            messages.info(request, 'Username or Password is Invalid')
            return redirect('signin')

    else:
        return render(request, 'signin.html')


@login_required(login_url='signin')
def signout(request):
    auth.logout(request)
    return redirect('signin')



def activate(request,uidb64, token):
    try:
        uid = force_str(urlsafe_base64_decode(uidb64))
        myuser = User.objects.get(pk=uid)
    except(TypeError, OverflowError,ValueError, User.DoesNotExist):
        myuser= None



    if myuser is not None and generate_token.check_token(myuser, token):
        myuser.is_active = True
        myuser.save()
        auth.login(request, myuser) 
        messages.success(request, "Your Account has been activated!!")
        user_model = User.objects.get(username=myuser.username)
        new_profile = Profile.objects.create(user=user_model, id_user=user_model.id)
        new_profile.save()
        return redirect('settings')   
     

    else:
        return render(request, "activation_failed.html")    


def example(request):
    threads = ThreadingTable.objects.by_user(user=request.user).prefetch_related('message1').order_by('timestamp') 
    arr=[]
    profile1=[]
    user_profile = Profile.objects.get(user=request.user)
    user_obect = User.objects.get(username = request.user.username)
    for i in range(len(threads)):
        arr.append(threads[i])
        y= threads[i].second_person.id
        d = Profile.objects.get(user=y)
        profile1.append(d.profile_img)

    context={
        'threads':threads,
        'profile1':profile1,
        "user_profile":user_profile,
    }
    return render(request, "example.html",context)
def example1(request):
    return render(request, "example1.html")





def channel(request, grp):
    group = Groups.objects.filter(name=grp).first()
    message=[]
    if group:
        message =Message.objects.filter(group = group)
    else:
        group = Groups(name=grp)
        group.save()
    return render(request, 'channel.html',{'group':grp,"message":message})



def example2(request):
    return render(request, "example2.html")