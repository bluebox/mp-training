#
# class  customers(APIView):
#     def get(self,request):
#         if len(request.data)==0:
#             query_set = Customers.objects.all()
#             serialized_query_set = CustomerSerializer(query_set, many=True)
#             return Response(serialized_query_set.data)
#         elif 'name' in request.data and 'email' in request.data:
#             return Response(CustomerSerializer(Customers.objects.filter(Q(name=request.data['name']) & Q(email=request.data['email'])),many=True).data)
#         elif 'name' in request.data:
#             return Response(CustomerSerializer(Customers.objects.filter(name=request.data['name']),many=True).data)
#         elif 'email' in request.data:
#             return Response(CustomerSerializer(Customers.objects.filter(email=request.data['email']),many=True).data)
#         else:
#             return Response("invalid field")
#
#
#     def post(self,request):
#         de_serialized_data=CustomerSerializer(data=request.data)
#         if de_serialized_data.is_valid():
#             de_serialized_data.save()
#             return Response("POST method was successful")
#         else:
#             return  Response("enter data is not valid")
#     def delete(self,request):
#         deleted=Customers.objects.filter(Q(name=request.data['name']) | Q(email=request.data['email'])).delete()
#         return Response(deleted)
#     def put(self,request):
#         try :
#             customer = Customers.objects.get(id=request.data['id'])
#
#             fields=list(Customers.objects.filter(id=request.data['id']).values())[0]
#             for i in fields:
#                 if i != 'id':
#                     setattr(customer, i, request.data[i])
#             customer.save()
#             return Response("success")
#         except:
#             return Response("error")
#     def patch(self,request):
#         try :
#             customer = Customers.objects.get(id=request.data['id'])
#             for i in request.data:
#                 if i != 'id':
#                     setattr(customer, i, request.data[i])
#             customer.save()
#             return Response("success")
#         except:
#             return Response("error")
#
#
# class orders(APIView):
#     def get(self,request,id=None):
#         if id:
#             query_set=get_object_or_404(Orders,)
#     def get(self, request):
#         if len(request.data) == 0:
#             query_set = Orders.objects.all()
#             serialized_query_set = OrdersSerializer(query_set, many=True)
#             return Response(serialized_query_set.data)
#         elif 'customer_id_id' in request.data and 'order_date' in request.data:
#             return Response(OrdersSerializer(
#                 Orders.objects.filter(Q(custmer_id_d=request.data['customer_id_id']) & Q(order_date=request.data['order_date'])),
#                 many=True).data)
#         elif 'customer_id_id' in request.data:
#             return Response(OrdersSerializer(Orders.objects.filter(name=request.data['name']), many=True).data)
#         elif 'order_date' in request.data:
#             return Response(OrdersSerializer(Orders.objects.filter(email=request.data['order_date']), many=True).data)
#         else:
#             return Response("invalid field")
#
#     def post(self, request):
#         de_serialized_data = CustomerSerializer(data=request.data)
#         if de_serialized_data.is_valid():
#             de_serialized_data.save()
#             return Response("POST method was successful")
#         else:
#             return Response("enter data is not valid")
#
#     def delete(self, request):
#         deleted = Customers.objects.filter(Q(name=request.data['name']) | Q(email=request.data['email'])).delete()
#         return Response(deleted)
#
#     def put(self, request):
#         try:
#             customer = Customers.objects.get(id=request.data['id'])
#
#             fields = list(Customers.objects.filter(id=request.data['id']).values())[0]
#             for i in fields:
#                 if i != 'id':
#                     setattr(customer, i, request.data[i])
#             customer.save()
#             return Response("success")
#         except:
#             return Response("error")
#
#     def patch(self, request):
#         try:
#             customer = Customers.objects.get(id=request.data['id'])
#             for i in request.data:
#                 if i != 'id':
#                     setattr(customer, i, request.data[i])
#             customer.save()
#             return Response("success")
#         except:
#             return Response("error")
#
#
# # @api_view(['GET','POST','DELETE','PUT','PATCH'])
# # def  customers(request):
# #     if request.method == 'GET':
# #         if len(request.data)==0:
# #             query_set = Customers.objects.all()
# #             serialized_query_set = CustomerSerializer(query_set, many=True)
# #             return Response(serialized_query_set.data)
# #         elif 'name' in request.data and 'email' in request.data:
# #             return Response(CustomerSerializer(Customers.objects.filter(Q(name=request.data['name']) & Q(email=request.data['email'])),many=True).data)
# #         elif 'name' in request.data:
# #             return Response(CustomerSerializer(Customers.objects.filter(name=request.data['name']),many=True).data)
# #         elif 'email' in request.data:
# #             return Response(CustomerSerializer(Customers.objects.filter(email=request.data['email']),many=True).data)
# #         else:
# #             return Response("invalid field")
# #             # return Response("Invalid http method")
# #
# #     elif request.method =='POST':
# #         de_serialized_data=CustomerSerializer(data=request.data)
# #         if de_serialized_data.is_valid():
# #             de_serialized_data.save()
# #             return Response("POST method was successful")
# #         else:
# #             return  Response("enter data is not valid")
# #     elif request.method == 'DELETE':
# #         deleted=Customers.objects.filter(Q(name=request.data['name']) | Q(email=request.data['email'])).delete()
# #         return Response(deleted)
# #     elif request.method == 'PUT':
# #         try :
# #             customer = Customers.objects.get(id=request.data['id'])
# #
# #             fields=list(Customers.objects.filter(id=request.data['id']).values())[0]
# #             for i in fields:
# #                 if i != 'id':
# #                     setattr(customer, i, request.data[i])
# #             customer.save()
# #             return Response("success")
# #         except:
# #             return Response("error")
# #     elif request.method == 'PATCH':
# #         try :
# #             customer = Customers.objects.get(id=request.data['id'])
# #             for i in request.data:
# #                 if i != 'id':
# #                     setattr(customer, i, request.data[i])
# #             customer.save()
# #             return Response("success")
# #         except:
# #             return Response("error")
#
#
# #
# #
# # @api_view(['GET'])
# # def  orders(request):
# #     query_set = Orders.objects.all()
# #     serialized_query_set = OrdersSerializer(query_set, many=True)
# #     return Response(serialized_query_set.data)
# #
# # @api_view(['GET'])
# # def  books(request):
# #     query_set = Books.objects.all()
# #     serialized_query_set = BooksSerializer(query_set, many=True)
# #     return Response(serialized_query_set.data)
# #
# # @api_view(['GET'])
# # def  order_items(request):
# #     query_set = Order_items.objects.all()
# #     serialized_query_set = Order_itemsSerializer(query_set, many=True)
# #     return Response(serialized_query_set.data)
# #
# #
# #
# # @api_view(['GET'])
# # def  authors(request):
# #     query_set = Authors.objects.all()
# #     serialized_query_set =AuthorsSerializer(query_set, many=True)
# #     return Response(serialized_query_set.data)
# #
# # @api_view(['GET'])
# # def  book_authors(request):
# #     query_set = Book_authors.objects.all()
# #     serialized_query_set = Book_authorsSerializer(query_set, many=True)
# #     return Response(serialized_query_set.data)
# #
# #
#
#
# #--------------Find customers who placed orders with total amount greater than the average order amount---
#
# # sum_quantity=Order_items.objects.values('order_id').annotate(sum=Sum(ExpressionWrapper(F('quantity')*F('book_id__price'),output_field=FloatField)))
# # print(sum_quantity.values())
#
# #---------------second maximum quantity------------------------
# #select max(quantity) from order_items where quantity <(select max(quantity) from order_items)