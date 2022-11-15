from django.contrib.auth.models import User

from customer.models import Customer
from customer.serializers import customerSerializer


def getCustomerDetails(username=None, format=None):
    user = User.objects.get(username=username)
    if not user.is_superuser:
        customer = Customer.objects.get(username=username)
        serialize = customerSerializer(customer)
        return serialize.data
    if user.is_superuser:
        cust = Customer.objects.all()
        serialize = customerSerializer(cust, many=True)
        return serialize.data
    else:
        return "failed"

def addCustomer(request):
        cust_id = User.objects.create_user(username=request.data['username'],
                                           email=request.data['email'], password=(request.data['password']))
        cust_id.save()
        request.data['customer_id'] = cust_id.id  # user id
        uname = cust_id.username
        serializer = customerSerializer(data=request.data)
        if serializer.is_valid(raise_exception=True):
            password = request.data['password']
            fullname = request.data['customer_name']
            phone = request.data['phone']
            email = request.data['email']
            address = request.data['address']
            cust = Customer(customer_id=cust_id, password=password,
                            customer_name=fullname, phone=phone, username=uname, email=email, address=address)
            cust.save()
            serialize = customerSerializer(cust)
            return serialize.data
        else:
            return "sorry unable to create customer"

def updateCustomer(request, username):
    password = request.data['password']
    fullname = request.data['customer_name']
    phone = request.data['phone']
    email = request.data['email']
    address = request.data['address']
    userid = User.objects.get(username=username)
    user = User(id=userid.id, username=username, password=password)
    user.save()
    cust = Customer(id=Customer.objects.get(customer_id=user).id, customer_id=user, password=password,
                    customer_name=fullname, phone=phone, username=username, email=email, address=address)
    cust.save()
    serialize = customerSerializer(cust)
    return serialize.data
