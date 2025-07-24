from ..models import MyBaseUser


def run():
    for i in range(1, 11):
        user = MyBaseUser.objects.create_user(
            username=f"user{i}",
            password = "password123",
        )
        user.save()