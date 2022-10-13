from django.contrib import admin
from .models import *

admin.site.register(client_jobs)
admin.site.register(client_details)
admin.site.register(client_fee_record)
admin.site.register(client_feedback_form)
admin.site.register(freelancer_proposals)
admin.site.register(freelancer_payment_details)
admin.site.register(freelancer_details)
admin.site.register(freelancer_feedback_form)
admin.site.register(client_contract_details)
admin.site.register(UserToken)



