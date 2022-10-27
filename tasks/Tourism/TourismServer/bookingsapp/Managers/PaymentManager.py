import uuid
from bookingsapp.models import PaymentDetails
from bookingsapp.serializers import PaymentSerializer
from rest_framework.response import Response
from rest_framework import status
from rest_framework.exceptions import APIException

def get_payments_by_admin(request):
    payment = PaymentDetails.objects.all()
    serializer = PaymentSerializer(payment, many=True)
    return Response(serializer.data)

def post_feedback_by_admin(request):
    # user = User.objects.get(email=request.user)
    request.data['transaction_id'] = str(uuid.uuid1())
    serializer = PaymentSerializer(data=request.data)
    if serializer.is_valid():
        serializer.save()
        return Response(serializer.data, status=status.HTTP_201_CREATED)
    raise APIException(serializer.errors)

def get_payment_by_id_by_admin(request, payment):
    serializer = PaymentSerializer(payment)
    return Response(serializer.data)

def edit_payment_by_admin(request, payment):
    serializer = PaymentSerializer(payment, data=request.data)
    if serializer.is_valid():
        serializer.save()
        return Response(serializer.data)
    raise APIException(serializer.errors)