from django.contrib import admin
from .models import *
# Register your models here.
admin.site.register(CustomerTable)
admin.site.register(LoanDetails)
admin.site.register(AccountTable)
admin.site.register(DebitCard)
admin.site.register(BeneficiaryDetails)
admin.site.register(CreditCard)
admin.site.register(TransactionTable)
# admin.site.register(Logs)
admin.site.register(EmployesTable)