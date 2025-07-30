# import os
# from datetime import datetime
# from django.db.models import Prefetch
#
# import django
#
# os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'mysite.settings')
#
# django.setup()
#
# from Web_World.models import  *
# from django.db.models import  Sum,Min,Max,Avg,ExpressionWrapper,F,IntegerField,FloatField,Count,Q
# from django.utils import timezone
# from django.db import transaction
# # a=Customers.objects.create(name="Prasad",email="Prasad123@gmail.com")
# # a.save()
# #
# # a=Customers.objects.create(name="CBN",email="CBN13@gmail.com")
# # a.save()
# # #
# # customers = [
# #     Customers(name="Anita", email="anita.kumar@example.com"),
# #     Customers(name="Ravi", email="ravi.sharma@example.com"),
# #     Customers(name="Sita", email="sita.patel@example.com"),
# #     Customers(name="Arjun", email="arjun.verma@example.com"),
# #     Customers(name="Meena", email="meena.rao@example.com"),
# #     Customers(name="Vijay", email="vijay.singh@example.com"),
# #     Customers(name="Lakshmi", email="lakshmi.das@example.com"),
# #     Customers(name="Rahul", email="rahul.jain@example.com"),
# #     Customers(name="Priya", email="priya.menon@example.com"),
# #     Customers(name="Amit", email="amit.gupta@example.com"),
# #     Customers(name="Neha", email="neha.sen@example.com"),
# #     Customers(name="Kiran", email="kiran.banerjee@example.com"),
# #     Customers(name="Sunil", email="sunil.reddy@example.com"),
# #     Customers(name="Divya", email="divya.nair@example.com"),
# #     Customers(name="Manoj", email="manoj.pillai@example.com"),
# #     Customers(name="Pooja", email="pooja.mishra@example.com"),
# #     Customers(name="Deepak", email="deepak.khan@example.com"),
# #     Customers(name="Shweta", email="shweta.kapoor@example.com"),
# #     Customers(name="Santosh", email="santosh.singh@example.com"),
# # ]
# # Customers.objects.bulk_create(customers)
#
#
#
# # b=Books.objects.create(title="wings of fire",price=568.23)
# # b.save()
# #
# # books=[
# #     Books(title="To Kill a Mockingbird", price=399.5),
# #     Books(title="1984", price=250),
# #     Books(title="The Great Gatsby", price=315.75),
# #     Books(title="The Alchemist", price=275.9),
# #     Books(title="Pride and Prejudice", price=289.99),
# #     Books(title="The Catcher in the Rye", price=310.45),
# #     Books(title="Sapiens", price=499),
# #     Books(title="The Hobbit", price=369.25),
# #     Books(title="Harry Potter and the Philosopher's Stone", price=450.1),
# #     Books(title="The Fault in Our Stars", price=330.8),
# #     Books(title="my words", price=222.22)
# # ]
# # Books.objects.bulk_create(books)
#
#
# # order = Orders.objects.create(customer=Customers.objects.get(id=1), order_date='2025-01-02')
# # order.save()
#
# # orders=[
# #     Orders(customer=Customers.objects.get(id=1), order_date='2024-02-01'),
# #     Orders(customer=Customers.objects.get(id=2), order_date='2027-03-15'),
# #     Orders(customer=Customers.objects.get(id=3), order_date='2029-05-20'),
# #     Orders(customer=Customers.objects.get(id=4), order_date='2024-06-10'),
# #     Orders(customer=Customers.objects.get(id=5), order_date='2027-07-18'),
# #     Orders(customer=Customers.objects.get(id=6), order_date='2029-08-05'),
# #     Orders(customer=Customers.objects.get(id=1), order_date='2024-09-23'),
# #     Orders(customer=Customers.objects.get(id=7), order_date='2027-10-11'),
# #     Orders(customer=Customers.objects.get(id=8), order_date='2029-11-30'),
# #     Orders(customer=Customers.objects.get(id=9), order_date='2024-12-19'),
# #     Orders(customer=Customers.objects.get(id=2), order_date='2027-01-27'),
# #     Orders(customer=Customers.objects.get(id=10), order_date='2029-03-03'),
# #     Orders(customer=Customers.objects.get(id=11), order_date='2024-04-14'),
# #     Orders(customer=Customers.objects.get(id=12), order_date='2027-06-09'),
# #     Orders(customer=Customers.objects.get(id=13), order_date='2029-08-16'),
# #     Orders(customer=Customers.objects.get(id=14), order_date='2024-10-25'),
# #     Orders(customer=Customers.objects.get(id=15), order_date='2027-12-05'),
# #     Orders(customer=Customers.objects.get(id=3), order_date='2029-02-22'),
# #     Orders(customer=Customers.objects.get(id=6), order_date='2024-04-08'),
# #     Orders(customer=Customers.objects.get(id=16), order_date='2027-07-14'),
# #     Orders(customer=Customers.objects.get(id=17), order_date='2029-09-30'),
# #     Orders(customer=Customers.objects.get(id=18), order_date='2024-11-11'),
# #     Orders(customer=Customers.objects.get(id=19), order_date='2027-01-03'),
# #     Orders(customer=Customers.objects.get(id=20), order_date='2029-03-19'),
# #     Orders(customer=Customers.objects.get(id=21), order_date='2024-05-27'),
# #     Orders(customer=Customers.objects.get(id=15), order_date='2027-08-01'),
# #     Orders(customer=Customers.objects.get(id=1), order_date='2029-10-13'),
# #     Orders(customer=Customers.objects.get(id=5), order_date='2024-12-07'),
# #     Orders(customer=Customers.objects.get(id=9), order_date='2027-02-17'),
# #     Orders(customer=Customers.objects.get(id=14), order_date='2029-06-21')
# # ]
# # Orders.objects.bulk_create(orders)
# #
# # oi = OrderItems.objects.create(order=Orders.objects.get(id=1), book=Books.objects.get(id=2), quantity=32)
# # oi.save()
# #
# # order_items = [
# #     Order_items(order=Orders.objects.get(id=32), book=Books.objects.get(id=2), quantity=15),
# #     Order_items(order=Orders.objects.get(id=33), book=Books.objects.get(id=3), quantity=10),
# #     Order_items(order=Orders.objects.get(id=34), book=Books.objects.get(id=4), quantity=20),
# #     Order_items(order=Orders.objects.get(id=35), book=Books.objects.get(id=5), quantity=25),
# #     Order_items(order=Orders.objects.get(id=36), book=Books.objects.get(id=7), quantity=12),
# #     Order_items(order=Orders.objects.get(id=37), book=Books.objects.get(id=8), quantity=8),
# #     Order_items(order=Orders.objects.get(id=38), book=Books.objects.get(id=9), quantity=18),
# #     Order_items(order=Orders.objects.get(id=39), book=Books.objects.get(id=10), quantity=22),
# #     Order_items(order=Orders.objects.get(id=40), book=Books.objects.get(id=11), quantity=6),
# #     Order_items(order=Orders.objects.get(id=41), book=Books.objects.get(id=1), quantity=14),
# #     Order_items(order=Orders.objects.get(id=42), book=Books.objects.get(id=2), quantity=17),
# #     Order_items(order=Orders.objects.get(id=43), book=Books.objects.get(id=3), quantity=11),
# #     Order_items(order=Orders.objects.get(id=44), book=Books.objects.get(id=4), quantity=9),
# #     Order_items(order=Orders.objects.get(id=45), book=Books.objects.get(id=5), quantity=13),
# #     Order_items(order=Orders.objects.get(id=46), book=Books.objects.get(id=7), quantity=5),
# #     Order_items(order=Orders.objects.get(id=47), book=Books.objects.get(id=8), quantity=16),
# #     Order_items(order=Orders.objects.get(id=48), book=Books.objects.get(id=9), quantity=19),
# #     Order_items(order=Orders.objects.get(id=49), book=Books.objects.get(id=10), quantity=7),
# #     Order_items(order=Orders.objects.get(id=50), book=Books.objects.get(id=11), quantity=24),
# #     Order_items(order=Orders.objects.get(id=51), book=Books.objects.get(id=1), quantity=21),
# #     Order_items(order=Orders.objects.get(id=52), book=Books.objects.get(id=2), quantity=20),
# #     Order_items(order=Orders.objects.get(id=53), book=Books.objects.get(id=3), quantity=9),
# #     Order_items(order=Orders.objects.get(id=54), book=Books.objects.get(id=4), quantity=10),
# #     Order_items(order=Orders.objects.get(id=55), book=Books.objects.get(id=5), quantity=14),
# #     Order_items(order=Orders.objects.get(id=56), book=Books.objects.get(id=7), quantity=18),
# #     Order_items(order=Orders.objects.get(id=57), book=Books.objects.get(id=8), quantity=22),
# #     Order_items(order=Orders.objects.get(id=58), book=Books.objects.get(id=9), quantity=6),
# #     Order_items(order=Orders.objects.get(id=59), book=Books.objects.get(id=10), quantity=23),
# #     Order_items(order=Orders.objects.get(id=60), book=Books.objects.get(id=11), quantity=12),
# #     Order_items(order=Orders.objects.get(id=61), book=Books.objects.get(id=1), quantity=15)
# # ]
# #
# # OrderItems.objects.bulk_create(order_items)
# #
#
#
# # a=Authors.objects.create(name="APJ abdul kalam")
# # a.save()
# #
# # authors=[
# #     Authors.objects.create(name="Harper Lee"),
# #     Authors.objects.create(name="George Orwell"),
# #     Authors.objects.create(name="F. Scott Fitzgerald"),
# #     Authors.objects.create(name="Paulo Coelho"),
# #     Authors.objects.create(name="Jane Austen"),
# #     Authors.objects.create(name="J.D. Salinger"),
# #     Authors.objects.create(name="Yuval Noah Harari"),
# #     Authors.objects.create(name="J.R.R. Tolkien"),
# #     Authors.objects.create(name="J.K. Rowling"),
# #     Authors.objects.create(name="John Green"),
# #     Authors.objects.create(name="Unknown Author")
# # ]
# # Authors.objects.bulk_create(authors)
#
#
# #
# # bk=Book_authors.objects.create(book=Books.objects.get(id=1),author=Authors.objects.get(id=2))
# #
# # book_authors=[
# #     Book_authors.objects.create(book=Books.objects.get(id=2), author=Authors.objects.get(id=3)),
# #     Book_authors.objects.create(book=Books.objects.get(id=3), author=Authors.objects.get(id=4)),
# #     Book_authors.objects.create(book=Books.objects.get(id=4), author=Authors.objects.get(id=5)),
# #     Book_authors.objects.create(book=Books.objects.get(id=5), author=Authors.objects.get(id=5)),
# #     Book_authors.objects.create(book=Books.objects.get(id=6), author=Authors.objects.get(id=7)),
# #     Book_authors.objects.create(book=Books.objects.get(id=7), author=Authors.objects.get(id=7)),
# #     Book_authors.objects.create(book=Books.objects.get(id=8), author=Authors.objects.get(id=9)),
# #     Book_authors.objects.create(book=Books.objects.get(id=9), author=Authors.objects.get(id=10)),
# #     Book_authors.objects.create(book=Books.objects.get(id=10), author=Authors.objects.get(id=11)),
# #     Book_authors.objects.create(book=Books.objects.get(id=11), author=Authors.objects.get(id=12)),
# #     Book_authors.objects.create(book=Books.objects.get(id=12), author=Authors.objects.get(id=7))
# # ]
# # Book_authors.objects.bulk_create(book_authors)
#
# # karthikl_orders=(OrderItems.objects.select_related('book','order__customer')
# #                  .filter(order__customer__name='Ravi')
# #                  .values('book__title','quantity','order__customer__name'))
# # print(karthikl_orders)
#
# # print(OrderItems.objects.aggregate(total_revenue=Sum(ExpressionWrapper(F('book__price') * F('quantity'),output_field=FloatField()))))
#
# # Customers.objects.create(name="Bhaskar",email="Bhaskar1234@Gmail.com")
# print(Customers.objects.exclude(id__in=Orders.objects.values('customer')))
#
# #List the top 5 best-selling products by total quantity sold.
# # top_5=OrderItems.objects.select_related('book').values('book','book__title').annotate(quantity_sum=Sum('quantity')).order_by('-quantity_sum')[:5]
# # print(top_5)
#
# #Retrieve the names and emails of users who placed an order in the last 7 days.
# a=Orders.objects.select_related('customer').annotate(diff=ExpressionWrapper(F('order_date')- timezone.now(),output_field=IntegerField())).filter(diff__lte=7)
# print(a)
# # Orders.objects.create(customer=Customers.objects.get(id=4),order_date='2025-07-12')
#
# # For each user, list their most recent order date.
# # l=Orders.objects.values('order_date').group_by('customer')[1]
# print("-"*30)
# print(Orders.objects.values('customer').annotate(latest_order=Max('order_date')).count())
# print(Orders.objects.all().count())
# # a=Orders.objects.values('customer','order_date').annotate(latest=Max('order_date')).values().group_by('customer')
# # # print(a.values())
# # for i in a.values():
# #     print(i['customer'],i['latest'])
#
#
#
# #creating indexes
# #modified in models.py and done with migrations
#
# # class Meta:
#     # models.Index(fields=['book', "author"])
# # a = Customers.objects.create(name="Nandu", email="Nandu234@gmail.com")
# # a.save()
#
# ###--------------------------SELECT, WHERE, ORDER BY, LIMIT----------------------------------------
# s=Books.objects.filter(price__lt=500)
# print(s.values)
#
# # select * from books;
# print(Books.objects.all())
# # select name from customers order by name desc;
# print( (Customers.objects.values('name').order_by('-name')))
# # select name from customers order by name asc;
# print((Customers.objects.values('name').order_by('name')))
# # select name,email from customers order by email asc;
# print((Customers.objects.values('name','email').order_by('email')))
# # select book ,title,price from books order by price;
# print((Books.objects.values('id','title','price').order_by('price')))
# # select book ,title,price from books order by price desc  limit 1;
# print((Books.objects.values('id','title','price').order_by('-price')[:1]))
# # select title from books where title like "19%";
# print((Books.objects.values('title').filter(title__istartswith='19')))
#
# # select book,title from books where title like "%" order by book desc limit 5 ;
# print(( Books.objects.values('id','title').filter(title__icontains='').order_by('-id')[:5]))
#
# # update customers set email = 'Prasda123@gmail.com' where email = 'Prasda13@gmail.com';
# # a=Customers.objects.get(email='Prasaduuu123@gmail.com')
# # a.email='Prasda123@gmail.com'
# # a.save()
#
# # delete from book_authors where author=2;
# print("deleted ")
# print((BookAuthors.objects.get(book=2).delete() ))
# BookAuthors.objects.create(book=Books.objects.get(id=2), author=Authors.objects.get(id=3))
#
# #INNER JOIN: Get orders with customer details
# print(Orders.objects.select_related('customer').values('customer__name','order_date') )
#
# #LEFT JOIN: Get all customers and their orders (if any)
# print(Orders.objects.prefetch_related('customer').values('customer__name','order_date'))
# #
#
# #Count
# # select C.customer,C.name,Count(O.order) from customers as C join orders as O on O.customer =C.customer group by C.customer;
# print(Orders.objects.select_related('customer').values('customer__name').annotate(count=Count('id')) )
# #
# # -- SUM ---
# # select B.title,SUM(O.quantity) from books as B join order_items as O on B.book=O.book group by B.title;
# # print((OrderItems.objects.select_related('book').values('book__title').annotate(count=Sum('quantity'))))
# #
# # -- MAX ---
# # select B.title,max(O.quantity) from books as B join order_items as O on B.book=O.book group by B.title;
# # print(OrderItems.objects.select_related('book').values('book__title').annotate(max_quantity=Sum('quantity')))
# # -- min--
# #  select B.title,min(O.quantity) from books as B join order_items as O on B.book=O.book group by B.title;
# # print((OrderItems.objects.select_related('book').values('book__title').annotate(min_quantity=Min('quantity'))))
# # -- avg------------
# #  select B.title,avg(O.quantity) from books as B join order_items as O on B.book=O.book group by B.title;
# # print((OrderItems.objects.select_related('book').values('book__title').annotate(avg=Avg('quantity'))))
# #
# #
# # -- --------- HAVING: ---------------------------
# # select B.title,sum(quantity) as total from books as B right join order_items as O on B.book=O.book group by B.title having total<100;
# # print((OrderItems.objects.select_related('book').values('book__title').annotate(sum=Sum('quantity')).filter(sum__lte=200) ))
#
#
# #----------------Using Q -------------------------------
# print(Books.objects.filter(Q(price__lte=250) & Q(price__gte=100)))
# print(Books.objects.filter(Q(price__lte=250) & Q(price__gte=100)).aggregate(sum=Sum('price')))
#
#
# #-----Commit,Rollback-----------------
# # with transaction.atomic():
# #     Customers.objects.create(name="vamsi",email="vamsi134567@gmail.com")
# #     # Customers.objects.create(name="Ravi", email="ravi.sharma@example.com")
#
# #---------using try chatch for exception handling--------------------------------
# with transaction.atomic():
#     # Customers.objects.create(name="kannapa", email="kannapa12@gmail.com")
#     try:
#         with transaction.atomic():
#             Customers.objects.create(name="vamsi", email="vamsi134567@gmail.com")
#     except:
#         print("saved paritially,rollbacked due to error")
#
# print("--"*60)
# print("---accessing all the data by taking each table as referance---")
#
# print('reference table:: Orders')
# O = Orders.objects.select_related('customer').prefetch_related(
#     Prefetch('orderitems_set', queryset=OrderItems.objects.select_related('book')
#              .prefetch_related(Prefetch('book__bookauthors_set',
#                                         queryset=BookAuthors.objects.select_related('author')))))
#
# for i in O:
#     print(i.customer.name, i.customer.email)
#     for j in i.orderitems_set.all():
#         print(j.book.id, j.book.title, j.book.price)
#         for k in j.book.bookauthors_set.all():
#             print(k.author.name)
#     print()
#     print()
# print("reference table:: Customers")
#
# c = Customers.objects.prefetch_related(
#     Prefetch('orders_set',queryset=Orders.objects.prefetch_related(
#         Prefetch('orderitems_set', queryset=OrderItems.objects.select_related('book').prefetch_related(
#             Prefetch('book__bookauthors_set',queryset=BookAuthors.objects.select_related('author')))))))
#
# for i in c:
#     print(i.name, i.email)
#     for j in i.orders_set.all():
#         print(j.id, j.order_date)
#         for k in j.orderitems_set.all():
#             print(k.book.title, k.book.price, k.quantity)
#             for z in k.book.bookauthors_set.all():
#                 print(z.author.name)
#     print()
#
#
#
#
#
