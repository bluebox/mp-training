from rest_framework import serializers
from recruitment.models import candidate_info , vaacancy,assesment,applying


class candidate_info_serializers(serializers.ModelSerializer):
    class Meta:
        model  = candidate_info
        fields = ('cid',
                  "name",
                  'contact',
                  'age',
                  'mail',
                  'gender',
                  'sheet_no',
                  'status',
                  'addharcard_no'
                  )



class assesment_serializers(serializers.ModelSerializer):
    class Meta:
        model  = assesment
        fields = ('cid',
                  "name",
                  'apptitute',
                  'codeing',
                  'interview1',
                  'interview2',
                  'result',
                                  )
    def decider(self,data):
        return assesment.objects.create(**data)



class vacancy_serializers(serializers.ModelSerializer):
    class Meta:
        model  = vaacancy
        fields = ('vid',
                  'designeation_id',
                  'salary',
                  'skill',
                  'experience',
                  'no_of_vacancy'
                                )
class applying_serializers(serializers.ModelSerializer):
    class Meta:
        model = applying
        fields =('cid','vid')

