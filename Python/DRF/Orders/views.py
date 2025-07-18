from django.contrib.auth import authenticate
from rest_framework import viewsets, status
from rest_framework.permissions import AllowAny
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework_simplejwt.tokens import RefreshToken
from django.contrib.auth.models import User

from .CustomPagination import CustomPagination
from .CustomPermission import CustomPermission, CustomPermissionForEmail
from .models import Product, Customer, Order, OrderItem
from .serializers import ProductSerializer, CustomerSerializer, OrderSerializer, OrderItemSerializer, UserSerializer


class UserViewSet(viewsets.ModelViewSet):
    queryset = User.objects.all()
    serializer_class = UserSerializer
    permission_classes = [CustomPermissionForEmail]


class ProductViewSet(viewsets.ModelViewSet):
    permission_classes = [CustomPermission]
    queryset = Product.objects.all()
    serializer_class = ProductSerializer
    # permission_classes = [AllowAny]


class CustomerViewSet(viewsets.ModelViewSet):
    queryset = Customer.objects.all()
    serializer_class = CustomerSerializer


class OrderViewSet(viewsets.ModelViewSet):
    queryset = Order.objects.all()
    serializer_class = OrderSerializer


class OrderItemViewSet(viewsets.ModelViewSet):
    queryset = OrderItem.objects.all()
    serializer_class = OrderItemSerializer


class QueryParmsExample(APIView):
    def get(request):
        vals = request.query_params.get('id')
        try:
            if vals:
                val = int(vals)
                print(val)
                customer = Customer.objects.get(id=val)
                serializer = CustomerSerializer(customer)
                return Response({'customer': serializer.data}, status=status.HTTP_200_OK)
            else:
                return Response({'error': 'ID is not provided'}, status=status.HTTP_400_BAD_REQUEST)
        except Exception:
            return Response({'error': f"No customer with id {val}"}, status=status.HTTP_404_NOT_FOUND)


class Join_example_to_get_customer_and_hisOrder(APIView):
    def get(self, request, pk):
        try:
            order = Order.objects.select_related('customer').get(customer__id=pk)
            print(order.customer.first_name)
        except Order.DoesNotExist:
            return Response({'error': 'Order not found'}, status=404)

        serializer = OrderSerializer(order)
        return Response({'customer': serializer.data['customer'], }, status=status.HTTP_200_OK)


class LoginView(APIView):
    permission_classes = [AllowAny]

    def post(self, request):
        username = request.data.get('username')
        password = request.data.get('password')

        if not username or not password:
            return Response({'error': 'Username and password are required'}, status=status.HTTP_400_BAD_REQUEST)

        user = authenticate(request, username=username, password=password)

        if user is not None:
            refresh = RefreshToken.for_user(user)
            return Response({
                'refresh': str(refresh),
                'access': str(refresh.access_token),
            }, status=status.HTTP_200_OK)
        else:
            return Response({'error': 'Invalid credentials'}, status=status.HTTP_401_UNAUTHORIZED)


class request_user(APIView):
    def get(self, request):
        serializer = UserSerializer(request.user)
        return Response(serializer.data)


class PaginationViewSets(viewsets.ModelViewSet):
    permission_classes = [AllowAny]
    queryset = User.objects.all()
    serializer_class = UserSerializer
    pagination_class = CustomPagination