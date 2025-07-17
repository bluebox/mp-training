from rest_framework import viewsets, status
from rest_framework.response import Response
from rest_framework.views import APIView

from .models import Product, Customer, Order, OrderItem
from .serializers import ProductSerializer, CustomerSerializer, OrderSerializer, OrderItemSerializer


class ProductViewSet(viewsets.ModelViewSet):
    queryset = Product.objects.all()
    serializer_class = ProductSerializer



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
    @staticmethod
    def get( request):
        vals = request.query_params.get('id')
        try:
            if vals:
                val=int(vals)
                print(val)
                customer = Customer.objects.get(id=val)
                serializer = CustomerSerializer(customer)
                return Response({'customer': serializer.data}, status=status.HTTP_200_OK)
            else:
                return Response({'error': 'ID is not provided'}, status=status.HTTP_400_BAD_REQUEST)
        except Exception :
            return Response({'error': f"No customer with id {val}"}, status=status.HTTP_404_NOT_FOUND)