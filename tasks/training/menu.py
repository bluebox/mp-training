# Building Restaurant Menu using Class in Python

class restaurant_item:
    menu = {"starters": [["chilli paneer",210],["paneer 65",200],["veg manuchurian",150]], 
            "biryanis": [["paneer_biryani",210],["mutton biryani",250],["paneer_family_pack",500],["mutton_family_pack",600],["special_paneer_biryani",250],["special_mutton_biryani",300]], "kebabs": [["veg_manuchurian",170],["tandoori_paneer",230],["paneer_garlic_kebab",210]], "curries": [["butter_paneer_boneless",220],["nizami_handi",210]], "indian_breads": [["tandoori_roti",50],["rumali_roti",30]], "desserts": [["double_ka_meta",100]],
            "beverages": [["jerra_soda",25],["salted_lassi",40],["sweet_lassi",40]]}

    def __init__(self, item, price):
        self.item = item
        self.price = price

    @classmethod
    def adding_new_item_to_menu(cls,category,item_name,price):
        restaurant_item.menu[category].append([item_name,price])

    @classmethod
    def changing_the_price(cls,category,item_name,new_price):
        for i in cls.menu[category]:
            if i[0]==item_name:
                i[1]=new_price
                break
    @classmethod
    def display_menu(cls,category):
        j=1
        for i in cls.menu[category]:
            print(j,i[0].center(30),i[1])
            j+=1

#adding the new items into the menu by using the adding_new_item_to_menu function
restaurant_item.adding_new_item_to_menu("starters","paneer_wings",150)
restaurant_item.adding_new_item_to_menu("beverages","double_chocolate_shake",190)
restaurant_item.adding_new_item_to_menu("biryanis","kaju_biryani",250)

#for changing the price of the already availble item
restaurant_item.changing_the_price("biryanis","kaju_biryani",270)
print(restaurant_item.menu)

category = int(input("Sir please choose the category you like to order \n (1) starters \n (2) biryanis \n (3) kebabs \n (4) curries \n (5) indian_breads \n (6) desserts \n (7) beverages \n"))
dict={1:"starters",2:"biryanis",3:"kebabs",4:"curries",5:"indian_breads",6:"desserts",7:"beverages"}
if restaurant_item.menu[dict[category]]:
    print("{} Menu".format(dict[category]))
    restaurant_item.display_menu(dict[category])
else:
    print("Sorry no available items in that category...")