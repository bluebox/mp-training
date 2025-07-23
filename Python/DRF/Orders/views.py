from django.contrib.auth import authenticate
from django.db.models import Count
from django_filters.rest_framework import DjangoFilterBackend
from rest_framework import viewsets, status, generics
from rest_framework.filters import SearchFilter
from rest_framework.permissions import AllowAny, IsAuthenticated
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework_simplejwt.tokens import RefreshToken
from django.contrib.auth.models import User

from .CustomPagination import CustomPagination
from .CustomPermission import CustomPermission, CustomPermissionForEmail
from .CustomThrottleClass import CustomThrottleClass
from .models import Product, Customer, Order, OrderItem, Product_review
from .serializers import ProductSerializer, CustomerSerializer, OrderSerializer, OrderItemSerializer, UserSerializer, \
    ReviewSerializers


class UserViewSet(viewsets.ModelViewSet):
    queryset = User.objects.all()
    serializer_class = UserSerializer
    permission_classes = [CustomPermissionForEmail]


class ProductViewSet(viewsets.ModelViewSet):
    permission_classes = [CustomPermission]
    queryset = Product.objects.all()
    serializer_class = ProductSerializer
    filter_backends = [DjangoFilterBackend, SearchFilter]
    filterset_fields = ['name', 'price']
    search_fields = ['name']

    permission_classes = [AllowAny]


class CustomerViewSet(viewsets.ModelViewSet):
    queryset = Customer.objects.all()
    serializer_class = CustomerSerializer
    throttle_scope = 'view_customer'


class OrderViewSet(viewsets.ModelViewSet):
    queryset = Order.objects.all()
    serializer_class = OrderSerializer
    throttle_classes = [CustomThrottleClass]


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

#
# @csrf_exempt
# class Student_view(ListModelMixin, CreateModelMixin, RetrieveModelMixin, UpdateModelMixin, DeletionMixin):
#     queryset = Student.objects.all()
#     serializer_class = StudentSerializer
#
#     def get(self, request, *args, **kwargs):
#         if 'pk' in kwargs:
#             return self.retrieve(request, *args, **kwargs)
#         return self.list(request, *args, **kwargs)
#
#     def post(self, request, *args, **kwargs):
#         return self.create(request, *args, **kwargs)
#
#     def put(self, request, *args, **kwargs):
#         return self.update(request, *args, **kwargs)
#
#     def delete(self, request, *args, **kwargs):
#         return self.destroy(request, *args, **kwargs)


# class Student_view(generics.ListCreateAPIView):
#     queryset = Student.objects.all()
#     serializer_class = StudentSerializer
#     permission_classes = [AllowAny]
#
# class Student_view_RUD(generics.RetrieveUpdateDestroyAPIView):
#     queryset = Student.objects.all()
#     serializer_class = StudentSerializer
#     permission_classes = [AllowAny]
#     lookup_field = 'id'

class Add_review(viewsets.ModelViewSet):
    queryset = Product_review.objects.all()
    serializer_class = ReviewSerializers
    permission_classes = [IsAuthenticated]


class Order_select_related(APIView):
    permission_classes = [AllowAny]
    def get(self,request):
        order=Order.objects.select_related('customer')
        serializer=OrderSerializer(order,many=True)
        return Response(serializer.data)

class Customer_prefetch_related(APIView):
    permission_classes = [AllowAny]
    def get(self,request):
        customer=Customer.objects.prefetch_related('orders')
        for cust in customer:
           for i in cust.orders.all():
               print(i.order_date)
        serializer=CustomerSerializer(customer,many=True)
        return Response(serializer.data)


class Annotate_example(APIView):
    def get(self, request):
        customers = Customer.objects.annotate(order_count=Count('orders'))

        data = []
        for customer in customers:
            print(customer.first_name, customer.order_count)
            data.append({
                'first_name': customer.first_name,
                'id':customer.id,
                'order_count': customer.order_count
            })

        return Response({'msg': 'success', 'customers': data})