from rest_framework.response import Response

from item.models import ContainsItem
from item.serializers import containsItemSerializer

from item.models import GraniteStore, Item
from item.serializers import storeSerializer, itemSerializer

from item.models import Cart
from item.serializers import cartSerializer

from customer.models import Customer


def getItems(request, pk):
    if pk is not None:
        item = ContainsItem.objects.get(contains_id=pk)
        serialize = containsItemSerializer(item)
        return serialize.data

    items = ContainsItem.objects.all()
    serialize = containsItemSerializer(items, many=True)
    return serialize.data

def addItems(request):
    contains_id = request.data["contains_id"]
    store_id = request.data["store_id"]
    item_id = request.data["item_id"]
    price = request.data["price"]
    item = ContainsItem(contains_id=contains_id, store_id=store_id, item_id=item_id, price=price)
    item.save()
    serialize = containsItemSerializer(item)
    return serialize.data

def updateItem(request, pk):
    contains_id = request.data["contains_id"]
    store_id = request.data["store_id"]
    item_id = request.data["item_id"]
    price = request.data["price"]
    item = ContainsItem(id=ContainsItem.objects.get(contains_id=pk).id, contains_id=contains_id, store_id=store_id,
                        item_id=item_id, price=price)
    item.save()
    serialize = containsItemSerializer(item)
    return serialize.data

def getStore(request, pk):
    if pk is not None:
        store = GraniteStore.objects.get(store_id=pk)
        serialize = storeSerializer(store)
        return Response(serialize.data)

    store = GraniteStore.objects.all()
    serialize = storeSerializer(store, many=True)
    return serialize.data

def addStore(request):
    serializer = storeSerializer(data=request.data)
    if serializer.is_valid():
        store_id = request.data['store_id']
        store_name = request.data['store_name']
        established_year = request.data['established_year']
        store_description = request.data['store_description']
        contact = request.data['contact']
        website = request.data['website']
        address = request.data['address']
        store = GraniteStore(store_id=store_id, store_name=store_name, established_year=established_year,
                             store_description=store_description,
                             contact=contact, website=website, address=address)
        store.save()
        return storeSerializer(store).data

def updateStore(request, pk):
    serializer = storeSerializer(data=request.data)
    if serializer.is_valid():
        store_id = request.data['store_id']
        store_name = request.data['store_name']
        established_year = request.data['established_year']
        store_description = request.data['store_description']
        contact = request.data['contact']
        website = request.data['website']
        address = request.data['address']
        store = GraniteStore(id=GraniteStore.objects.get(store_id=store_id).id, store_name=store_name,
                             established_year=established_year, store_description=store_description,
                             contact=contact, website=website, address=address)
        store.save()
        return storeSerializer(store).data

def getCart(request, customer_id):
    print("got customer data")
    serializer = cartSerializer(items)
    return serializer.data

def addToCart(request):
    customer_id = request.COOKIES.get('username')
    cust_id = Customer.objects.get(username=customer_id)
    item_id = ContainsItem.objects.get(contains_id=request.data)
    try:
        item = Cart.objects.get(customer_id=cust_id, item_id=item_id)
        if item is not None:
            c = Cart(id=item.id, customer_id=cust_id, item_id=item_id, quantity=(item.quantity + 1))
            c.save()
            print(c.quantity)
    except:
        c = Cart(customer_id=cust_id, item_id=item_id)
        c.save()
        print('error occured')
    return 'added successfully'

def updateCart(request):
    customer_id = request.COOKIES.get('username')
    cust_id = Customer.objects.get(username=customer_id)
    item_id = ContainsItem.objects.get(contains_id=request.data['contains_id'])
    value = request.data['value']
    try:
        item = Cart.objects.get(customer_id=cust_id, item_id=item_id)
        if item is not None:
            if item.quantity + value > 0:
                c = Cart(id=item.id, customer_id=cust_id, item_id=item_id, quantity=(item.quantity + value))
                c.save()
            else:
                c = Cart(id=item.id)
                c.delete()
            print(c.quantity)
    except Exception:
        print('error occured')
    return "cart Updation "


def deleteCart(request, customer_id):
    print('value', customer_id)

    if customer_id == '-1':

        cartItem = Cart.objects.all()
        cartItem.delete()
        return 'deleted all items'


    else:
        contains_id = customer_id
        print(contains_id)
        customer_id = request.COOKIES.get('username')
        cust_id = Customer.objects.get(username=customer_id)
        item_id = ContainsItem.objects.get(contains_id=int(contains_id))
        item = Cart.objects.get(customer_id=cust_id, item_id=item_id)
        item.delete()
        return 'successfull'
