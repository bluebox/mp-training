'''apps file '''

from django.apps import AppConfig


class AppointmentConfig(AppConfig):
    '''app config'''
    default_auto_field = 'django.db.models.BigAutoField'
    name = 'appointment'
