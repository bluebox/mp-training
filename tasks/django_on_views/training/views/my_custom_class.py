from django.http import HttpResponse
class parent():
    def father(self):
        return HttpResponse("I AM HIS FATHER")
    def mother(self):
        return HttpResponse("I am his mother")