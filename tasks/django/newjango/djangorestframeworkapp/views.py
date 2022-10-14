from django.shortcuts import render
from django.http import HttpResponse, JsonResponse
from rest_framework.parsers import JSONParser
from .models import UserDetails
from .serializers import UserSerializers, ModSeri
from django.views.decorators.csrf import csrf_exempt

from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status



from rest_framework.views import APIView


from rest_framework import mixins
from rest_framework import generics


from rest_framework.authentication import SessionAuthentication, BasicAuthentication
# from rest_framework.permissions import
# Create your views here.
@csrf_exempt
def func_based_views(request):

    if request.method == "GET":
        data = UserDetails.objects.all()
        serializer = UserSerializers(data, many = True) # To get specified attribute from UserDetails class :use "ModSeri" instead of "UserSerilizers"
        return JsonResponse(serializer.data, safe = False)
    elif request.method == "POST":
        data = JSONParser().parse(request)
        serializer = UserSerializers(data = data)

        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data, status=201)
        return JsonResponse(serializer.errors, status=400)


@csrf_exempt
def func_based_views_with_pk(request, name):

    try:
        fulldata = UserDetails.objects.get(first_name = name)
    except UserDetails.DoesNotExist:
        return HttpResponse(status=404)

    if request.method == "GET":
        serializer = UserSerializers(fulldata)
        return JsonResponse(serializer.data)

    elif request.method == "PUT":
        data = JSONParser().parse(request)
        serializer = UserSerializers(fulldata, data= data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data)
        return JsonResponse(serializer.errors, status=400)
    elif request.method == "DELETE":
        fulldata.delete()
        return HttpResponse(status=204)


@api_view(["GET", "POST"])
def func_based_api_views(request):

    if request.method == "GET":
        data = UserDetails.objects.all()
        serializer = UserSerializers(data, many = True)
        return Response(serializer.data) #this whole code is similar to first function
    elif request.method == "POST":
        # data = JSONParser().parse(request)
        serializer = UserSerializers(data = request.data)

        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)



@api_view(["GET","PUT","DELETE"])
def func_based_api_views_with_pk(request, name):

    try:
        fulldata = UserDetails.objects.get(first_name = name)
    except UserDetails.DoesNotExist:
        return HttpResponse(status=status.HTTP_404_NOT_FOUND)

    if request.method == "GET":
        serializer = UserSerializers(fulldata)
        return Response(serializer.data)

    elif request.method == "PUT":
        # data = JSONParser().parse(request)
        serializer = UserSerializers(fulldata, data= request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
    elif request.method == "DELETE":
        fulldata.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)



class ClassBasedViews(APIView):

    def get(self, request):
        datafromdb = UserDetails.objects.all()
        serializer = UserSerializers(datafromdb, many= True)
        return Response(serializer.data)
    def post(self,request):
        serializer = UserSerializers(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)



class ClassBasedViewsWithPk(APIView):
     def get_object(self, name):
         try:
             return UserDetails.objects.get(first_name = name)
         except UserDetails.DoesNotExist:
             return HttpResponse(status=status.HTTP_404_NOT_FOUND)


     def get(self,request, name):
         user_data = self.get_object(name)
         serializer = UserSerializers(user_data)
         return Response(serializer.data)

     def put(self,request, name):
         user_data = self.get_object(name)
         serializer = UserSerializers(user_data, data=request.data)
         if serializer.is_valid():
             serializer.save()
             return Response(serializer.data)
         return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

     def delete(self, request, name):
         user_data = self.get_object(name)
         user_data.delete()
         return Response(status=status.HTTP_204_NO_CONTENT)




class GenericAPIView(generics.GenericAPIView, mixins.ListModelMixin, mixins.CreateModelMixin, mixins.UpdateModelMixin,
                     mixins.RetrieveModelMixin, mixins.DestroyModelMixin):
    serializer_class = UserSerializers
    queryset = UserDetails.objects.all()

    lookup_field = 'first_name'
    def get(self, request, first_name):
        if first_name:
            return self.retrieve(request)
        else:
            return self.list(request)

    def post(self, request):
        return self.create(request)

    def put(self,request, first_name):
        return self.update(request, first_name)

    def delete(self,request, first_name):
        return self.destroy(request, first_name)


