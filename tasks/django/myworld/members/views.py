from django.shortcuts import render,get_object_or_404
from django.http import Http404
from django.urls import reverse
from django.http import HttpResponse,HttpResponseRedirect
from .models import Choice, Question

def index(request):
  latest_question_list=Question.objects.order_by('-pub_date')[:5]
  # output=', '.join([q.question_text for q in latest_question_list])
  context={'latest_question_list':latest_question_list}
  return render(request,'index.html',context)

def detail(request, question_id):
    # return HttpResponse("You're looking at question %s." % question_id)
    try:
        question=Question.objects.get(pk=question_id)
    except Question.DoesNotExist:
      raise Http404("Question does not exist")

    # question=get_object_or_404(Question,pk=question_id)

    return render(request,'detail.html',{'question':question})


def results(request, question_id):
    # response = "You're looking at the results of question %s."
    # return HttpResponse(response % question_id)
    question=get_object_or_404(Question,pk=question_id)
    return render(request,'results.html',{'question':question})

def vote(request, question_id):
    # return HttpResponse("You're voting on question %s." % question_id)
    question=get_object_or_404(Question,pk=question_id)
     
    try:
      selected_choice=question.choice_set.get(pk=request.POST['choice'])
    except (KeyError,Choice.DoesNOTExist):

        return render(request,'details.html',{'question':question,'error_message':"you didn't select a choice.",})


    else:
      selected_choice.votes+=1
      selected_choice.save()

      return HttpResponseRedirect(reverse('members:result',args=(question.id)))