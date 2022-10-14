from django.shortcuts import render,redirect
from django.http import HttpResponse,HttpResponseRedirect
from modelsapp.models import *
from django.template import loader
from django.contrib.auth import  logout
from django.contrib import messages
from django.urls import reverse


def homepage(request):
    return HttpResponse(render(request, 'modelsapp/home.html'))


def login(request):
    return  HttpResponse(render(request, 'modelsapp/employee_login.html'))
# Create your views here.


def employee(request):
    mail = request.POST["email"]
    passw = request.POST["password"]
    table = EmployesTable.objects.all().values()
    for i in table:
        if (i["emp_email"] == mail) and (i["emp_password"] == int(passw)):
            obj = CustomerTable.objects.all()
            return HttpResponse(render(request, 'modelsapp/employee_interface.html'))
            # else:
            #     return HttpResponse("enter valid password")
        else:
            continue
    return HttpResponse("Once Check email and password")


def user_login(request):
    return HttpResponse(render(request, 'modelsapp/customer_login.html'))


def user_login_details(request):
    mail = request.POST["customer_email"]
    passw = request.POST["customer_password"]
    table = CustomerTable.objects.all().values()
    for i in table:
        if (i["customer_email"] == mail) and (i["customer_password"] == int(passw)):
            global obj
            global bal
            global template
            obj = CustomerTable.objects.get(customer_email = i["customer_email"])
            bal = AccountTable.objects.filter(customer=obj.customer_id)
            template = loader.get_template('modelsapp/customer_profile.html')

            #
            #
            if bal.count() > 1:
                data = {
                    "userdata": obj,
                    "savings_account": bal[0],
                    "current_account": bal[1],
                }
                return HttpResponse(template.render(data, request))

            else:
                if bal[0].account_type == "savings":
                    data = {
                        "userdata": obj,
                        "savings_account": bal[0],
                    }
                    return HttpResponse(template.render(data, request))
                else:
                    data = {
                        "userdata": obj,
                        "current_account": bal[0],
                    }
                    return HttpResponse(template.render(data, request))

        else:
            continue

    messages.info(request, 'username or password not correct')
    return redirect('/customer_login')
    # return HttpResponse("hello")
def view_balance(request):
    acc_type = request.POST['drop-down']
    acc_num = request.POST['account-number']
    # return HttpResponse(acc_num)
    # print(bal.count())
    # amount_temp = loader.get_template('modelsapp/customer_profile.html')
    if bal.count() > 1:
        if (bal[0].account_type == acc_type) and (bal[0].account_number == int(acc_num)):
            balance = bal[0].amount
            # return HttpResponse(balance)
            data = {
            "user_bal" : balance,
            "userdata": obj,
            "savings_account": bal[0],
            "current_account": bal[1],
            }
            return HttpResponse(template.render(data, request))
        else:
            balance = bal[1].amount
            # return HttpResponse(balance)

            data = {
                "user_bal": balance,
                "userdata": obj,
                "savings_account": bal[0],
                "current_account": bal[1],
            }
            return HttpResponse(template.render(data, request))
    else:
        if bal[0].accout_type == acc_type:
            balance = bal[0].amount

            data = {
                "user_bal": balance,
                "userdata": obj,
                "current_account": bal[0],
            }
            return HttpResponse(template.render(data, request))
        else:
            return messages.error(request, 'Account Doesnt exist')
#
# def log_out_page(request):
#     return redirect('customer_login')

# def log_out_page(request):
#     pre_url = request.META.get('HTTP_REFERER')  # Here
#
#     return render(request, 'log_out_page/customer_login.html')

def UserLogout(request):
    logout(request)
    return redirect('user_login')


def EmployeeLogout(request):
    logout(request)
    return redirect('login')