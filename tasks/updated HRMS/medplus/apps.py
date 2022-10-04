from django.apps import AppConfig


class MedplusConfig(AppConfig):
    default_auto_field = 'django.db.models.BigAutoField'
    name = 'medplus'

    def ready(self):
        import medplus.signals
