from django.shortcuts import render
from django.http import HttpResponse


def without_param(request):
    return HttpResponse("This is a home page")


def with_one_param(request, param):
    return HttpResponse("we are using parameter " + str(param))


def with_slug(request, param):
    return HttpResponse("We are using parameter " + param)


def with_uuid(request, param):
    return HttpResponse("We are using parameter " + str(param))


def with_con(request, param):
    return HttpResponse("We are using converter" + str(param))


def with_repath(request, parameter):
    return HttpResponse("We are using repath" + str(parameter))


def with_extra(request, param):
    return HttpResponse("We are using extra parameter " + param)