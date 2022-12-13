from django.urls import path, include
from rest_framework.routers import DefaultRouter

from post import views

#
router = DefaultRouter()
# router.register("choice", views.ChoiceViewSet)
urlpatterns = [
    path('post', views.AddPost.as_view()),
    path('view-post', views.ViewPost.as_view()),
    path('upload-img', views.ImageView.as_view()),
    path('add-like', views.update_likes),
    path('add-shares', views.update_shares),
    path("comment", views.CommentView.as_view()),
    path('get-post-with-filter', views.get_posts_with_filter),
    path('evaluate', views.EvaluateView.as_view()),
    path("api/", include(router.urls)),
    path('bulkupdate', views.update),
    path('get-updates', views.get_updates),
    path("upload-files", views.Uplod.as_view()),
    path("bulkupload", views.BulkUpload.as_view()),
    path('chunks', views.Chunks.as_view()),
    path('excel-download', views.get_excel_file),
    path('backend-excel', views.get_backend_excel),
    # path('sample', views.sample),
    # path('user', views.UserView.as_view())

]
