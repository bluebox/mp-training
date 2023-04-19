from django.shortcuts import render
from rest_framework.response import Response
from rest_framework.decorators import api_view, permission_classes
from rest_framework.permissions import IsAdminUser, IsAuthenticated
from .models import Product
from .serializers import ProductSerializer, UserSerializer, UserSerializerWithToken
from rest_framework_simplejwt.serializers import TokenObtainPairSerializer
from rest_framework_simplejwt.views import TokenObtainPairView
from django.contrib.auth.models import User
from rest_framework import status
from django.contrib.auth.hashers import make_password
from django.http import Http404
from django.core.paginator import Paginator, PageNotAnInteger, EmptyPage
# Create your views here.



@api_view(['GET'])
def getProducts(request):
    query = request.query_params.get('keyword')
    print(query)
    if query == '' or query == 'null' or query == None:
        products = Product.objects.all()
    else:
        products = Product.objects.filter(name__icontains=query).order_by('-created_at')
    
    page = request.query_params.get('page')
    paginator = Paginator(products, 4)

    try:
        products = paginator.page(page)
    except PageNotAnInteger:
        products = paginator.page(1)
    except EmptyPage:
        products = paginator.page(paginator.num_pages)
    
    if page == None or page == 'null':
        page = 1

    page = int(page) 


    serializer = ProductSerializer(products, many=True)
    return Response({'products': serializer.data, 'page': page, 'pages': paginator.num_pages})


@api_view(['GET'])
def getProduct(request, pk):
    product = Product.objects.get(pk=pk)
    serializer = ProductSerializer(product)
    return Response(serializer.data)


@api_view(['POST',])
@permission_classes([IsAdminUser])
def createProduct(request):

    request.data['user'] = request.user.id
    serializer = ProductSerializer(data=request.data)
    if serializer.is_valid():
        serializer.image = request.FILES.get('image')
        serializer.save()
        return Response(serializer.data)
    return Response(serializer.errors)

@api_view(['DELETE'])
@permission_classes([IsAdminUser])
def deleteProduct(request, pk):
    try:
        product = Product.objects.get(pk=pk)
    except Product.DoesNotExist:
        raise Http404
    product.delete()
    return Response(status=status.HTTP_204_NO_CONTENT)

@api_view(['PUT',])
@permission_classes([IsAdminUser])
def updateProduct(request, pk):
    data = request.data
    try: 
        product = Product.objects.get(pk=pk)
        product.name = data['name']
        product.brand = data['brand']
        product.price = data['price']
        product.category = data['category']
        product.countInStock = data['countInStock']
        product.description = data['description']
        if request.FILES.get('image'):
            product.image = request.FILES.get('image')
        product.save()
        serializer = ProductSerializer(product, many=False)
        return Response(serializer.data)
    except Product.DoesNotExist:
        return Response({'detail': 'Product not found'}, status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET',])
@permission_classes([IsAuthenticated])
def getUser(request, pk):
    try:
        user = User.objects.get(pk=pk)
        serializer = UserSerializer(user)
        return Response(serializer.data)
    except User.DoesNotExist:
        return Response({'detail': 'User Does Not Exists'}, status=status.HTTP_404_NOT_FOUND)

@api_view(['GET',])
@permission_classes([IsAuthenticated])
def getUserProfile(request):
    user = request.user
    serializer = UserSerializer(user)
    return Response(serializer.data)

@api_view(['GET',])
@permission_classes([IsAdminUser])
def getUserDetail(request, pk):
    try:
        user = User.objects.get(pk=pk)
        serializer = UserSerializer(user)
        return Response(serializer.data)
    except User.DoesNotExist:
        return Response({'detail': 'User Does Not Exists'})


@api_view(['GET',])
@permission_classes([IsAdminUser])
def getUsers(request):
    user = User.objects.all()
    serializer = UserSerializer(user, many=True)
    return Response(serializer.data)

@api_view(['POST'])
def registerUser(request):
    data = request.data
    try:
        user = User.objects.create_user(
            username=data['username'],
            email = data['email'],
            password = data['password'],
            first_name = data['first_name'],
            last_name = data['last_name']
        )

        serializer = UserSerializer(user)
        return Response(serializer.data, status=status.HTTP_201_CREATED)
    except:
        message = {'detail': 'User with this username already exist'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['DELETE'])
@permission_classes([IsAuthenticated])
def deleteUser(request, pk):
    try:
        user = User.objects.get(pk=pk)
        user.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
    except User.DoesNotExist:
        raise Http404


@api_view(['PUT',])
@permission_classes([IsAuthenticated])
def updateUserProfile(request):
    user = request.user
    data = request.data
    try: 
        user.email = data['email']
        user.first_name = data['first_name']
        user.last_name = data['last_name']
        if data['password'] != '':
            user.password = make_password(data['password'])
        user.save()
        serializer = UserSerializer(user)
        return Response(serializer.data)
    except:
        message = {'detail': 'User with this username already exist'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


@api_view(['PUT',])
@permission_classes([IsAdminUser])
def updateUserDetail(request, pk):
    data = request.data
    try: 
        user = User.objects.get(pk=pk)
        user.username = data['username']
        user.email = data['email']
        user.first_name = data['first_name']
        user.last_name = data['last_name']
        user.is_staff = data['isAdmin']
        user.save()
        serializer = UserSerializer(user, many=False)
        return Response(serializer.data)
    except:
        message = {'detail': 'User with this username already exist'}
        return Response(message, status=status.HTTP_400_BAD_REQUEST)


class MyTokenObtainPairSerializer(TokenObtainPairSerializer):


    def validate(self, attrs):
        data = super().validate(attrs)

        serializer = UserSerializerWithToken(self.user).data
        for k,v in serializer.items():
            data[k] = v

        return data


class MyTokenObtainPairView(TokenObtainPairView):
    serializer_class = MyTokenObtainPairSerializer

