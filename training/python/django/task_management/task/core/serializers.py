from rest_framework import serializers
from django.contrib.auth import get_user_model
from .models import Team, TeamMember, Project, Task, TaskComment, TaskAssignment

User = get_user_model()

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('id', 'username', 'email', 'first_name', 'last_name', 'role')


class RegisterSerializer(serializers.ModelSerializer):
    password = serializers.CharField(write_only=True, min_length=8)

    class Meta:
        model = User
        fields = ('id', 'username', 'email', 'first_name', 'last_name', 'password', 'role')

    def create(self, validated_data):
        user = User.objects.create_user(
            username=validated_data['username'],
            email=validated_data['email'],
            first_name=validated_data.get('first_name', ''),
            last_name=validated_data.get('last_name', ''),
            role=validated_data.get('role', 'member')
        )
        user.set_password(validated_data['password'])
        user.save()
        return user


class TeamSerializer(serializers.ModelSerializer):
    class Meta:
        model = Team
        fields = '__all__'


class TeamMemberSerializer(serializers.ModelSerializer):
    user = UserSerializer(read_only=True)

    class Meta:
        model = TeamMember
        fields = '__all__'

class TeamAssignSerializer(serializers.ModelSerializer):

    class Meta:
        model = TeamMember
        fields = '__all__'


class ProjectSerializer(serializers.ModelSerializer):
    team = TeamSerializer(read_only=True)
    created_by = UserSerializer(read_only=True)

    class Meta:
        model = Project
        fields = '__all__'

class ProjectPDSerializer(serializers.ModelSerializer):

    class Meta:
        model = Project
        fields = "__all__"


class TaskSerializer(serializers.ModelSerializer):
    project_obj = ProjectSerializer(read_only=True)
    created_by_obj = UserSerializer(read_only=True)

    class Meta:
        model = Task
        fields = '__all__'

# class TaskViewSerializer(serializers.ModelSerializer):
#     project = ProjectSerializer(read_only=True)
#     created_by = UserSerializer(read_only=True)
#
#     class Meta:
#         model = Task
#         fields = '__all__'

class TaskCommentSerializer(serializers.ModelSerializer):
    user_obj = UserSerializer(read_only=True)

    class Meta:
        model = TaskComment
        fields = '__all__'


class TaskAssignmentSerializer(serializers.ModelSerializer):
    user_obj = UserSerializer(read_only=True)
    task_obj = TaskSerializer(read_only=True)

    class Meta:
        model = TaskAssignment
        fields = '__all__'


# class TaskAssignmentViewSerializer(serializers.ModelSerializer):
#     user = UserSerializer(read_only=True)
#     task = TaskViewSerializer(read_only=True)
#
#     class Meta:
#         model = TaskAssignment
#         fields = '__all__'

class TaskIndSerializer(serializers.ModelSerializer):
    class Meta:
        model = Task
        fields = ['id', 'title', 'description', 'status', 'priority', 'due_date']

class IndividualTaskViewSerializer(serializers.ModelSerializer):
    task = TaskIndSerializer(read_only=True)

    class Meta:
        model = TaskAssignment
        fields = '__all__'

class ProjectTaskSerializer(serializers.ModelSerializer):
    class Meta:
        model = Project
        fields = '__all__'


class AllTaskViewSerializer(serializers.ModelSerializer):
    project = ProjectTaskSerializer(read_only=True)
    created_by = UserSerializer(read_only=True)

    class Meta:
        model = Task
        fields = '__all__'