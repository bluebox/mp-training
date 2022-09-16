from django.db import models
from django.core.validators import validate_email

# Create your models here.


class Customer(models.Model):
    cust_id = models.CharField(max_length=20, primary_key=True)
    first_name = models.CharField(max_length=50, null=False, blank=False)
    last_name = models.CharField(max_length=50, blank=False)
    contact = models.CharField(max_length=13, null=False, blank=False)
    email = models.CharField(max_length=50, null=False, blank=False, unique=True, validators=[validate_email])
    address = models.CharField(max_length=250, null=False, blank=False)

    class Meta:
        verbose_name_plural = "customer"

    def __str__(self):
        return self.first_name


class Manufacturer(models.Model):
    company_id = models.CharField(max_length=20, primary_key=True)
    company_name = models.CharField(max_length=50, null=False, blank=False)
    contact = models.CharField(max_length=13, null=False, blank=False)
    email = models.CharField(max_length=50, null=False, blank=False, unique=True, validators=[validate_email])
    address = models.CharField(max_length=250, null=False, blank=False)

    class Meta:
        verbose_name_plural = "manufacturer"

    def __str__(self):
        return self.company_name


class Doctor(models.Model):
    doc_id = models.CharField(max_length=20, primary_key=True)
    first_name = models.CharField(max_length=50, null=False, blank=False)
    last_name = models.CharField(max_length=50, blank=False)
    specialization = models.CharField(max_length=250, null=False, blank=False)
    contact = models.CharField(max_length=13, null=False, blank=False)
    email = models.CharField(max_length=50, null=False, blank=False, unique=True, validators=[validate_email])
    address = models.CharField(max_length=250, null=False, blank=False)

    class Meta:
        verbose_name_plural = "doctor"

    def __str__(self):
        return self.first_name


class Employee(models.Model):
    emp_id = models.CharField(max_length=20, primary_key=True)
    first_name = models.CharField(max_length=50, null=False, blank=False)
    last_name = models.CharField(max_length=50, blank=False)
    doj = models.DateField(null=False, blank=False)
    designation = models.CharField(max_length=50, null=False, blank=False)
    job_type = models.CharField(max_length=50, null=False, blank=False)
    salary = models.IntegerField(null=False, blank=False)
    contact = models.CharField(max_length=13, null=False, blank=False)
    email = models.CharField(max_length=50, null=False, blank=False, unique=True, validators=[validate_email])
    address = models.CharField(max_length=250, null=False, blank=False)

    class Meta:
        verbose_name_plural = "employee"

    def __str__(self):
        return self.first_name


class Distributor(models.Model):
    dist_id = models.CharField(max_length=20, primary_key=True)
    dist_name = models.CharField(max_length=50, null=False, blank=False)
    contact = models.CharField(max_length=13, null=False, blank=False)
    email = models.CharField(max_length=50, null=False, blank=False, unique=True, validators=[validate_email])
    address = models.CharField(max_length=250, null=False, blank=False)

    class Meta:
        verbose_name_plural = "distributor"

    def __str__(self):
        return self.dist_name


class Drug(models.Model):
    drug_id = models.CharField(max_length=20, primary_key=True)
    drug_name = models.CharField(max_length=50, null=False, blank=False)
    dist_id = models.ForeignKey(Distributor, on_delete=models.CASCADE, null=True, blank=True)
    company_id = models.ForeignKey(Manufacturer, on_delete=models.CASCADE, null=True, blank=True)
    mrp = models.DecimalField(max_digits=5, decimal_places=5, null=False, blank=False)
    discount = models.DecimalField(max_digits=5, decimal_places=5, null=False, blank=False)
    stock = models.IntegerField(null=False, blank=False)
    mfg_date = models.DateField(null=False, blank=False)

    class Meta:
        verbose_name_plural = "drug"

    def __str__(self):
        return self.drug_name


class Sales(models.Model):
    sale_id = models.CharField(max_length=20, primary_key=True)
    sale_date = models.DateField(null=False, blank=False)
    emp_id = models.ForeignKey(Employee, on_delete=models.CASCADE, null=False, blank=False)
    cust_id = models.ForeignKey(Customer, on_delete=models.CASCADE, null=False, blank=False)
    drug_id = models.ForeignKey(Drug, on_delete=models.CASCADE, null=False, blank=False)
    quantity = models.IntegerField(null=False, blank=False)
    price = models.DecimalField(max_digits=10, decimal_places=10, null=False, blank=False)

    class Meta:
        verbose_name_plural = "sales"

    def __str__(self):
        return self.sale_id


class Prescribe(models.Model):
    prescription_id = models.CharField(max_length=20, primary_key=True)
    cust_id = models.ForeignKey(Customer, on_delete=models.CASCADE, null=False, blank=False)
    doc_id = models.ForeignKey(Doctor, on_delete=models.CASCADE, null=False, blank=False)
    drug_id = models.ForeignKey(Drug, on_delete=models.CASCADE, null=False, blank=False)
    quantity = models.IntegerField(null=False, blank=False)

    class Meta:
        verbose_name_plural = "prescribe"

    def __str__(self):
        return self.cust_id


class Purchase(models.Model):
    purchase_id = models.CharField(max_length=20, primary_key=True)
    purchase_date = models.DateField(null=False, blank=False)
    drug_id = models.ForeignKey(Drug, on_delete=models.CASCADE, null=False, blank=False)
    drug_name = models.CharField(max_length=100, null=False, blank=False)

    class Meta:
        verbose_name_plural = "purchase"

    def __str__(self):
        return self.purchase_id


class Supply(models.Model):
    purchase_id = models.ForeignKey(Purchase, on_delete=models.CASCADE, null=False, blank=False)
    purchase_date = models.DateField(null=False, blank=False)
    drug_id = models.ForeignKey(Drug, on_delete=models.CASCADE, null=False, blank=False)
    drug_name = models.CharField(max_length=100, null=False, blank=False)
    dist_id = models.ForeignKey(Distributor, on_delete=models.CASCADE, null=True, blank=True)
    company_id = models.ForeignKey(Manufacturer, on_delete=models.CASCADE, null=True, blank=True)
    quantity = models.IntegerField(null=False, blank=False)
    price = models.DecimalField(max_digits=10, decimal_places=10, null=False, blank=False)

    class Meta:
        verbose_name_plural = "supply"

    def __str__(self):
        return self.purchase_id


class Cart(models.Model):
    drug_id = models.ForeignKey(Drug, on_delete=models.CASCADE, null=False, blank=False)
    cust_id = models.ForeignKey(Customer, on_delete=models.CASCADE, null=False, blank=False)
    quantity = models.IntegerField(null=False, blank=False)
    price = models.DecimalField(max_digits=10, decimal_places=10, null=False, blank=False)

    class Meta:
        verbose_name_plural = "cart"

    def __str__(self):
        return self.drug_id


class Order(models.Model):
    order_id = models.CharField(max_length=20, primary_key=True)
    order_date = models.DateField(null=False, blank=False)
    cust_id = models.ForeignKey(Customer, on_delete=models.CASCADE, null=False, blank=False)
    prescription_id = models.ForeignKey(Prescribe, on_delete=models.CASCADE, null=False, blank=False)
    drug_id = models.ForeignKey(Drug, on_delete=models.CASCADE, null=False, blank=False)
    payment_mode = models.CharField(max_length=10)

    class Meta:
        verbose_name_plural = "order"

    def __str__(self):
        return self.order_id


class Invoice(models.Model):
    bill_id = models.CharField(max_length=20, primary_key=True)
    billing_date = models.DateField(null=False, blank=False)
    order_id = models.ForeignKey(Order, on_delete=models.CASCADE, null=False, blank=False)
    cust_id = models.ForeignKey(Customer, on_delete=models.CASCADE, null=False, blank=False)
    drug_id = models.ForeignKey(Drug, on_delete=models.CASCADE, null=False, blank=False)
    payment_mode = models.CharField(max_length=10)
    quantity = models.IntegerField(null=False, blank=False)
    price = models.DecimalField(max_digits=10, decimal_places=10, null=False, blank=False)

    class Meta:
        verbose_name_plural = "invoice"

    def __str__(self):
        return self.bill_id
