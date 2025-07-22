from django.contrib.auth import get_user_model
from ..models import product, order, order_items
from django.utils import timezone
import random

def add_users():
    User = get_user_model()

    # Create Users
    user1 = User.objects.create_user(username='alice', email='alice@example.com', password='alicepass')
    user2 = User.objects.create_user(username='bob', email='bob@example.com', password='bobpass')

    # Create Products
    products = [
        {"name": "Laptop", "description": "High performance laptop", "price": 899.99, "stock": 15},
        {"name": "Smartphone", "description": "Latest Android phone", "price": 699.99, "stock": 25},
        {"name": "Headphones", "description": "Noise cancelling headphones", "price": 199.99, "stock": 50},
        {"name": "Monitor", "description": "27 inch LED monitor", "price": 299.99, "stock": 10},
        {"name": "Keyboard", "description": "Mechanical keyboard", "price": 89.99, "stock": 30},
    ]

    product_objs = []
    for prod in products:
        p = product.objects.create(
            name=prod['name'],
            description=prod['description'],
            price=prod['price'],
            stock=prod['stock']
        )
        product_objs.append(p)

    # Create Orders and Order Items
    for i in range(3):  # create 3 orders
        user_obj = random.choice([user1, user2])
        order_obj = order.objects.create(user=user_obj)

        selected_products = random.sample(product_objs, k=2)
        for prod in selected_products:
            qty = random.randint(1, 5)
            order_items.objects.create(order=order_obj, product=prod, quantity=qty)

    print("Sample users, products, orders, and order_items added successfully!")


def run():
    # add_users()

