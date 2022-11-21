import random
from customer.models import Customer
from order.models import Order, OrderedItems
from order.serializers import orderedItemsSerializer, orderSerializer

from employee.models import Employee
from item.models import ContainsItem
from order.models import itemsDelivery
from vehicle.models import Vehicle


def getOrder(request, pk):
    print('hello')
    if pk is not None:
        print(pk)
        orderobj = Order.objects.get(order_id=pk)
        order = OrderedItems.objects.filter(order_id=orderobj)
        print(order)
        serialize = orderedItemsSerializer(order, many=True)
        return serialize.data
    if request.COOKIES.get('isAdmin') == 'True':
        order = Order.objects.select_related().order_by('-order_id')
        serialize = orderSerializer(order, many=True)
        return serialize.data

    uorders = Order.objects.select_related().filter(
        customer_id=Customer.objects.get(username=request.COOKIES.get('username'))).order_by('-order_id')
    order = orderSerializer(uorders, many=True)
    return order.data

def addOrder(request):
    customer = Customer.objects.get(username=request.COOKIES.get('username'))
    order_time = request.data['order_time']
    deliveryAddress = request.data['deliveryAddress']
    item_ids = request.data['contains_ids']
    orderid = Order.objects.all().last().order_id + 1
    order = Order(order_id=orderid, customer_id=customer, order_time=order_time,
                  delivery_address=deliveryAddress)
    order.save()
    print('ordered successfully')
    driver = Employee.objects.filter(role_id=Role.objects.get(role_name='driver'))
    vehicle = Vehicle.objects.all()
    print('came till delivery')
    delivery = itemsDelivery(order_id=order, driver_id=driver[random.randint(0, len(driver) - 1)],
                             vehicle_id=vehicle[random.randint(0, len(vehicle) - 1)])
    delivery.save()
    print(orderid, customer, order_time, deliveryAddress, item_ids)

    for id in item_ids:
        item = ContainsItem.objects.get(contains_id=id)
        orderitem = OrderedItems(order_id=order, contains_id=item)
        orderitem.save()
    return 'order placed successfully'
