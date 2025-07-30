from rest_framework import generics, permissions, status
from rest_framework.views import APIView
from rest_framework.response import Response
from django.contrib.auth import get_user_model
from .models import Task, TaskComment, TaskAssignment, Team, Project, TeamMember
from .serializers import (
    RegisterSerializer,
    UserSerializer,
    TaskSerializer,
    TaskCommentSerializer,
    TaskAssignmentSerializer,
    TeamSerializer,
    ProjectSerializer, TaskViewSerializer, TaskAssignmentViewSerializer, TeamMemberSerializer,
    IndividualTaskViewSerializer, AllTaskViewSerializer, TeamAssignSerializer
)

User = get_user_model()


# Register new user
class RegisterAPIView(generics.CreateAPIView):
    queryset = User.objects.all()
    serializer_class = RegisterSerializer


# Authenticated user's profile
class UserDetailView(APIView):
    permission_classes = [permissions.IsAuthenticated]

    def get(self, request):
        serializer = UserSerializer(request.user)
        return Response(serializer.data)

    def put(self, request):
        serializer = UserSerializer(request.user,data=request.data,partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class AllUserProfiles(APIView):
    permission_classes = [permissions.IsAuthenticated]
    def get(self, request):
        queryset = User.objects.exclude(id=request.user.id).order_by("role")
        serializer = UserSerializer(queryset,many=True)
        return Response(serializer.data)

# Lead can view tasks of their team
class LeadTaskView(APIView):
    permission_classes = [permissions.IsAuthenticated]

    def get(self, request):
        if request.user.role != 'lead':
            return Response({"error": "Only team leads can access this."}, status=403)
        tasks = Task.objects.select_related("project").filter(project__team__teammember__user=request.user)
        return Response(TaskSerializer(tasks, many=True).data)


# Admin can view all tasks
class AdminAllTasksView(APIView):
    permission_classes = [permissions.IsAuthenticated]

    def get(self, request):
        if request.user.role != 'admin':
            return Response({"error": "Only admin can access this."}, status=403)
        tasks = TaskAssignment.objects.all()
        return Response(TaskAssignmentViewSerializer(tasks, many=True).data)

class AllTasksView(APIView):
    permission_classes = [permissions.IsAuthenticated]

    def get(self, request):
        tasks = Task.objects.all()
        return Response(AllTaskViewSerializer(tasks, many=True).data)


# Create new task (lead or admin)
class TaskCreateView(APIView):
    permission_classes = [permissions.IsAuthenticated]

    def post(self, request):
        if request.user.role not in ['lead', 'admin']:
            return Response({"error": "Only leads or admins can create tasks."}, status=403)

        data = request.data.copy()
        data['created_by_obj'] = request.user.id
        data['project_obj'] = Project.objects.get(id =int(data['project']))
        serializer = TaskSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=201)
        return Response(serializer.errors, status=400)


# View, Update, Delete a specific task
class TaskDetailView(APIView):
    permission_classes = [permissions.IsAuthenticated]

    def get_object(self, pk):
        try:
            return Task.objects.get(pk=pk)
        except Task.DoesNotExist:
            return None

    def get(self, request, pk):
        task = self.get_object(pk)
        if not task:
            return Response({"error": "Task not found"}, status=404)
        return Response(TaskSerializer(task).data)

    def put(self, request, pk):
        task = self.get_object(pk)
        if not task:
            return Response({"error": "Task not found"}, status=404)
        serializer = TaskSerializer(task, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=400)

    def delete(self, request, pk):
        task = self.get_object(pk)
        if not task:
            return Response({"error": "Task not found"}, status=404)
        if task.created_by != request.user and request.user.role != 'admin':
            return Response({"error": "You don't have permission."}, status=403)
        task.delete()
        return Response(status=204)


# Add a comment to a task
class TaskCommentCreateView(APIView):
    permission_classes = [permissions.IsAuthenticated]

    def post(self, request, task_id):
        data = request.data.copy()
        data['task'] = task_id
        data['user_obj'] = request.user
        serializer = TaskCommentSerializer(data=data)

        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=201)
        return Response(serializer.errors, status=400)


# Assign task to user
class AssignTaskView(APIView):
    permission_classes = [permissions.IsAuthenticated]

    def post(self, request):
        print(request.user.role)
        if request.user.role not in ['lead', 'admin']:
            return Response({"error": "Only leads or admins can assign tasks."}, status=403)

        data = request.data.copy()
        data['user_obj'] = request.user
        data['task_obj'] = Task.objects.get(id=int(data['task']))
        serializer = TaskAssignmentSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=201)
        return Response(serializer.errors, status=400)

    def get(self,request):
        queryset = TaskAssignment.objects.all()
        serializer = TaskAssignmentSerializer(queryset,many=True)
        return Response(serializer.data)

class IndividualTaskView(APIView):
    def get(self,request):
        queryset = TaskAssignment.objects.filter(user=request.user.id)
        serializer = IndividualTaskViewSerializer(queryset,many=True)
        return Response(serializer.data)

# Team list/create
class TeamView(APIView):
    permission_classes = [permissions.IsAuthenticated]
    def get(self,request):
        queryset = Team.objects.all()
        serializer = TeamSerializer(queryset,many=True)
        return Response(serializer.data)

    def post(self,request):
        serializer = TeamSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=201)
        return Response(serializer.errors, status=400)

    def put(self,request,pk):
        queryset = Team.objects.get(id=pk)
        serializer = TeamSerializer(queryset,data=request.data,partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=201)
        return Response(serializer.errors, status=400)


# Project list/create
class ProjectView(generics.ListCreateAPIView):
    queryset = Project.objects.all()
    serializer_class = ProjectSerializer
    permission_classes = [permissions.IsAuthenticated]

class TeamMembersView(APIView):
    def get(self, request):
        # Admin: return all users except admins
        if request.user.role == 'admin':
            queryset = User.objects.exclude(role='admin')
            serializer = UserSerializer(queryset, many=True)
            return Response(serializer.data)

        # Non-admin: return members of the latest team the user joined
        teams = TeamMember.objects.select_related('team', 'user').filter(user=request.user).order_by('joined_at')
        if not teams.exists():
            return Response({'detail': 'User is not in any team.'}, status=status.HTTP_404_NOT_FOUND)

        latest_team = teams.last().team  # You need the team object, not the TeamMember ID
        team_members = TeamMember.objects.filter(team=latest_team).select_related('user').distinct()
        serializer = TeamMemberSerializer(team_members, many=True)

        # return Response(serializer.data)

        return Response({
            # 'team': team.name,
            'members': serializer.data
        })

    def post(self, request):
        serializer = TeamAssignSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors,status=400)

class UnassignedTasksVIew(APIView):
    def get(self, request):
        unassigned_tasks = Task.objects.exclude(id__in=TaskAssignment.objects.values('task_id'))
        if not unassigned_tasks:
            return Response({"error": "No unassigned tasks found"}, status=404)
        serializer = TaskSerializer(unassigned_tasks, many=True)
        return Response(serializer.data)