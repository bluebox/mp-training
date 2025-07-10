from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.
posts=[{"title":"Why python is the best","content":"Python has a strong community and a plethora of supporting packages"},
	   {"title":"Why python is the best","content":"Python has a strong community and a plethora of supporting packages"},
	   {"title":"Why python is the best","content":"Python has a strong community and a plethora of supporting packages"}]

credentials={"name":"Kanishka Sriramoju","email":'kanishka.sriramoju@gmail.com','X':"kanishka","title":'Kanishka'}

def HomeBuilder(request):
	data={'posts':posts,'title':'Kanishka'}
	return render(request,'blog/my_blog_home.html',data)

def AboutPageBuilder(request):
	return render(request,'blog/about_Page.html',credentials)
