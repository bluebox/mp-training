const express = require('express');
const cors = require('cors');
const { Pool } = require('pg');
const bcrypt = require('bcrypt');
const nodemailer = require('nodemailer');

const otpGenerator = require('otp-generator');


const app = express();
app.use(express.json());
app.use(cors({
  origin: "http://localhost:5173", // or whatever your React app is running on
  credentials: true
}));

const transporter = nodemailer.createTransport({
  service: 'gmail',
  auth: {
    user: 'pinnurisaikrishna@gmail.com',
    pass: 'rmqrddjyakgswwxh'
  }
});


const pool = new Pool({

  user: 'postgres',
  host: 'localhost',
  database: 'studentsboat',
  password: 'your_new_password',
  port: 5432
});

const pool1 = new Pool({

  user: 'postgres',
  host: 'localhost',
  database: 'fullstack',
  password: 'your_new_password',
  port: 5432
});

app.post('/send-otp', async (req, res) => {
  const { email } = req.body;
  console.log("email is ", email);
  const otp = otpGenerator.generate(6, { uppercase: false, specialChars: false });

  try {
    await pool1.query(
      'insert into email_otp(email,otp,created_at) values($1,$2,now())', [email, otp]
    );
    await transporter.sendMail({
      from: 'pinnurisaikrishna@gmail.com',
      to: email,
      subject: 'Your otp code',
      text: `Your OTP is ${otp}. It will expire in 5 minutes`
    });
    res.json({ message: 'OTP sent' });
  }
  catch (err) {
    console.error(err);
    res.status(500).json({ message: "Error in sending" });
  }


})

app.post('/verify-otp', async (req, res) => {
  const { email, otp } = req.body;
  try {
    const q3 = await pool1.query(`select * from email_otp where email=$1 order by created_at desc limit 1`, [email])
    if (q3.rows.length === 0) {
      return res.status(400).json({ message: 'No OTP found' });
    }
    const storedOtp = q3.rows[0];
    const currentTime = new Date();
    const otpTime = new Date(storedOtp.created_at);
    const diff = (currentTime - otpTime) / (1000 * 60);
    //console.log("see the details ", storedOtp, " ", otp, " ", diff);
    if (diff > 5) {
      return res.status(400).json({ message: 'OTP expired' });
    }
    if (storedOtp.otp === otp) {
      res.json({ message: 'OTP verified' });
    }
    else {
      res.status(400).json({ message: 'Invalid OTP' });
    }
  }
  catch (err) {
    console.error(err);
    res.status(500).json({ message: 'Error verifying string' });
  }
});


app.post('/signup', async (req, res) => {

  try {
    const { username, password, emailId, country, phone_number } = req.body;
    const hash_password = await bcrypt.hash(password, 10);


    const result = await pool1.query(
      "INSERT INTO signup(username, password, emailId, country, phonenumber) VALUES ($1, $2, $3, $4, $5) RETURNING *",
      [username, hash_password, emailId, country, phone_number]
    );


    res.status(201).json(result.rows[0]);
    console.log("this is the result ", result);
  }
  catch (err) {
    res.status(400).json({ error: err.message });
  }

});

app.post('/login', async (req, res) => {
  try {
    const { EmailId, password } = req.body;

    console.log(" newest checking login post");

    const check = await pool1.query("select * from signup where emailId=$1 or phonenumber=$1 or username=$1", [EmailId]);

    console.log("checking login post", check);

    if (check.rows.length === 0) {
      return res.status(404).json({ message: 'User not found' });
    }
    const isvalid_password = await bcrypt.compare(password, check.rows[0].password);
    if (!isvalid_password) {
      return res.status(404).json({ message: 'invalid password' });
    }
    console.log("login succesul");
    return res.status(200).json({ message: 'Login successful', user: check.rows[0] });

  }
  catch (err) {
    console.error(err);
    res.status(500).json({ message: 'Server error' });
  }
})


app.get('/users', async (req, res) => {
  try {
    const query1 = await pool.query("select * from Users where user_type='student'");
    const query2 = await pool.query("select * from Users where user_type='host'");
    const query3 = await pool.query("select * from Users where user_type='admin'");

    res.json({
      table1: query1.rows,
      table2: query2.rows,
      table3: query3.rows,
    })
  }
  catch (err) {
    console.error(err.message);
    res.status(500).send('server error');
  }
});
app.get('/properties', async (req, res) => {
  try {
    const q1 = await pool.query(`SELECT 
        p1.host_id,
        p1.property_id,
        place_type,
        title,
        country,
        city,
        district,
        street_address,
        pincode,
        image_url,
        room_type,
        number_of_beds,
        is_attached_bathroom,
        bathroom_type,
        price_per_night,
        max_guests,
        available_month,
        is_available,
        price,
        ARRAY_AGG(a.amenity) AS amenities
      FROM 
        Properties p1
      INNER JOIN 
        Property_photos ON p1.property_id = Property_photos.property_id
      INNER JOIN 
        Rooms ON p1.property_id = Rooms.property_id
      INNER JOIN 
        Availability ON p1.property_id = Availability.property_id
      LEFT JOIN 
        Property_amenities pa ON p1.property_id = pa.property_id
      LEFT JOIN 
        Amenities a ON pa.amenity_id = a.amenity_id
      GROUP BY 
        p1.host_id, p1.property_id, place_type, title, country, city, district,
        street_address, pincode, image_url, room_type, number_of_beds,
        is_attached_bathroom, bathroom_type, price_per_night, max_guests,
        available_month, is_available, price;
      `)
    res.json(q1.rows);
  }
  catch (err) {
    console.error(err.message);
    res.status(500).send('server error');
  }
})
app.listen(5000, () => {
  console.log("server running on 5000 port");
})