# from rest_framework_simplejwt.authentication import JWTAuthentication
#
# class CustomAuth(JWTAuthentication):
#     def authenticate(self, request):
#         header = self.get_header(request)
#         if header is None:
#             return None
#
#         raw_token = self.get_raw_token(header)
#         if raw_token is None:
#             return None
#
#         try:
#             validated_token =
