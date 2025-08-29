import logo from './logo.svg';
import './App.css';
import { useEffect, useState } from 'react';
import { FaInstagram, FaYoutube, FaTwitter, FaGithub, FaWhatsapp ,FaBars} from 'react-icons/fa';
import { createPortal } from 'react-dom';

function ProfileOut({ isOpen, onClose, children }){
  if (!isOpen) return null;

  return createPortal(
    <div style={{
      position: 'fixed',
      top: '0px',
      left: '0px',
      right: '0px',
      bottom: '0px',
      backgroundColor: 'rgba(0, 0, 0, 0.5)',
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center'
    }}>
      <div style={{
        background: 'white',
        padding: '20px',
        borderRadius: '8px',
        width:'80%',height:'80%'
      }}>
        <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
        <button style={{background:'red' ,borderRadius:'0%'}} onClick={onClose}>Close</button></div>
        {children}
        
      </div>
    </div>,
    document.body
  );
  
}




function App() {
  const [bgColor, setBgColor] = useState("#a8a2a2ff");
  const [fontColor, setFontcolor] = useState('#223633ff');
  const [headBarLength, setHeadBarLemgth] = useState("0px")
  const [isOpen, setIsOpen] = useState(false);

  useEffect(() => {
    const handleMouseMove = () => {
      setHeadBarLemgth(String((window.scrollY *1150 )/2450) + 'px')
      console.log("values", headBarLength)
      if (window.scrollY < 300) {
        setBgColor("#a8a2a2ff");
        setFontcolor('black')

      } else if (window.scrollY > 300 && window.scrollY < 1050) {
        setBgColor('#050e0cff');
        setFontcolor('#0c7a57ff');
      }
      else if (window.scrollY > 1050 && window.scrollY < 1600) {
        setBgColor('#050e0cff');
        setFontcolor('#a1af60ff');
      }
      else {
        setBgColor('#222423ff');
        setFontcolor('#f3f5eaff');
      }
    }
    window.addEventListener("scroll", handleMouseMove);

    return () => window.removeEventListener("scroll", handleMouseMove);
  });




  return (<div style={{ background: bgColor, transition: 'background 5s ease', color: fontColor ,transition: 'color 1s ease'}}  >

    <nav style={{ background: 'red', position: 'fixed', background: "rgba(255, 255, 255, 0.2)", backdropFilter: "blur(2px)" }} >
      <div style={{ borderTop: "4px solid ", width: headBarLength, transition: "border-color 4s ease" }} ></div>
    </nav>
    <nav className='nav-bar' style={{
      background: "rgba(255, 255, 255, 0.2)", backdropFilter: "blur(2px)",
      display: 'flex', position: 'fixed', width: '100%', marginTop: '4px'
    }}>

      <img style={{ width: '50px', height: '50px', borderRadius: '50%', margin: '10px' ,cursor:'pointer'}} src="/MyPic.jpeg" alt="Profile" onClick={() => setIsOpen(true)}/>
      <ProfileOut isOpen={isOpen} onClose={() => setIsOpen(false)}>
        <h2 style={{textAlign:'center'}}>My Profile</h2>
        <div style={{display:'flex' ,gap:'15px'}}>
        <img style={{ width: '180px', height: '180px', margin: '10px' ,cursor:'pointer'}} src='/MyPic.jpeg' alt='Profile pic'/>
      <div>
        <b>
      <label>Name </label><br/>
      <label>Role </label><br/>
      <label>Age</label><br/>
      <label>Email</label><br/>
      <label>Phone_Number</label><br/>
      <label>LinkedIn_Profile</label><br/>
      <label>GitHub</label><br/>
      <label>Languages_Spoken</label><br/>
      <label>Skills</label><br/>
      <label>About Me</label><br/> </b>
      </div>
      <div>
        <b>
      <label>:</label><br/>
      <label>: </label><br/>
      <label>:</label><br/>
      <label>:</label><br/>
      <label>:</label><br/>
      <label>:</label><br/>
      <label>:</label><br/>
      <label>:</label><br/>
      <label>:</label><br/>
      <label>:</label><br/></b>
      </div>
      <div>
      <label>S S N S V D PRASAD </label><br/>
      <label>Developer </label><br/>
      <label>YOU DON'T NEED iT</label><br/>
      <label>saisankula369@gmail.com </label><br/>
      <label>+91 9133868020 </label><br/>
     <label>link</label><br/>
     <label>durgaprasad9133 </label><br/>
      <label>ENGLISH and TELUGU </label><br/>
      <label>Python,java,sql,html,css,react </label><br/>
      <label>I’m a dedicated software developer with hands-on experience in building full-stack web applications using Python, Django, React, MySQL, and Redis. I specialize in developing scalable, efficient, and user-friendly solutions that meet real-world business needs.
With a strong foundation in backend development and API integration, I aim to deliver clean, maintainable code that adds real value.
If you're looking for a reliable developer to turn your ideas into powerful web applications, I’m ready to help!</label>
      </div>

      </div>
      </ProfileOut>
      <div style={{ marginLeft: 'auto' }} >
        <button style={{ width: '150px', height: '30px', margin: '10px', cursor: 'pointer' }} >Curriculum Vitae | CV</button>
        
        
          </div>
   <FaBars style={{ width: '30px', height: '30px', position: 'relative', margin: '20px'}} size={14} />
    </nav>


    <div style={{ display: 'flex', gap: '50px' }}>

      <div>
        <nav style={{
          display: 'flex', flexDirection: 'column', gap: '10px', marginLeft: '20px'
          , marginBottom: 'auto', justifyContent: 'flex-end', height: '100vh',
          position: 'fixed'
        }}>
          <FaInstagram title="Instagram" size={24} style={{cursor: "pointer" }} onClick= {()=>{ window.open("https://instagram.com/prasad_saisankula", "_blank");}} color={fontColor} />
          <FaYoutube title="Youtube" size={24} style={{cursor: "pointer" }} onClick= {()=>{ window.open("https://youtube.com", "_blank");}} color={fontColor} />
          <FaTwitter title= "Twitter" size={24} color={fontColor} />
          <FaGithub title="GitHub" size={24} style={{cursor: "pointer" }} onClick= {()=>{ window.open("https://github.com/durgaprasad9133", "_blank");}} color={fontColor} />
          <FaWhatsapp title="what's app" style={{cursor: "pointer" }} size={24} onClick= {()=>{ window.open("https://wa.me/919133868020", "_blank");}} color={fontColor} />
          <div style={{ borderLeft: "2px solid " + String(fontColor), height: "150px", position: 'relative', left: '11px' }}></div>
        </nav>
      </div>
      <div>



        <div id='Home' style={{ margin: '30px', marginTop: '145px', padding: '20px' }}>
          <h1 style={{ fontSize: '42px', fontWeight: '700px', margin: 0, lineHeight: 1.2 }}>Good day,</h1>
          <h1 style={{ fontSize: '42px', margin: 0, lineHeight: 1.2 }}>I am Durga Prasad,</h1>
          <h2 style={{ fontSize: '28px', color: 'blue', margin: 0, lineHeight: 1.2 }} >A Python Developer</h2>
          <p>I’m a skilled mobile app developer with over 4 years of experiencein the IT industry. <br /> Using React Native, I create full-stack mobile apps that cater to the needs of diverse clients. My expertise lies in developing hybrid apps for both Android and iOS platforms, ensuring that users have access to efficient and user-friendly applications. <br /> With a deep understanding of the mobile app development process, I am able to deliver high-quality results that exceed expectations. <br /> If you’re looking for a reliable mobile app developer to bring your ideas to life, I’m here to help!</p>
          <button style={{ width: '100px', height: '40px', cursor: 'pointer' }}  size={24} onClick= {()=>{ window.open("https://wa.me/919133868020", "_blank");}}> Contact me</button>
        </div>


        <div id='MyPortFolio' style={{ margin: '90px', marginTop: '145px' }}>

          <h1 >My Portfolio</h1><div style={{ borderTop: "2px solid black", width: '400px' }} ></div><br />
          <div style={{ display: 'flex' }}>
            <div>
              <p1>Greetings, I'm S S N S V D Prasad, a passionate and dedicated software developer with hands-on experience in building efficient and scalable web applications. My core expertise lies in backend development using Python and Django, coupled with strong proficiency in working with MySQL, Redis, and modern frontend frameworks like React.</p1>
              <br /><br /><p1>I currently work at Optival Solutions (MEDPLUS), where I contribute to full-stack development, API design, database management, and performance optimization. My role involves collaborating across teams to develop reliable, user-centric solutions that align with business goals and enhance user experience.</p1>
              <br /><br /><p1>My journey into software development began with a deep interest in solving real-world problems through code. Over time, I’ve refined my skills through academic projects, internships, and now in a professional setting. With a strong foundation in data structures and algorithms, and a keen eye for clean architecture, I aim to build software that is both maintainable and impactful.</p1>
              <br/><p>Having worked in collaborative, fast-paced environments, I value teamwork, continuous learning, and writing clean, testable code. I’m always eager to take on new challenges, explore modern technologies, and grow as a well-rounded developer.</p>
              <ul style={{ display: 'flex', gap: '30px' }}>
                <li>JavaScript</li>
                <li>React </li>
                <li>Django</li>
                <li>Mysql</li>

              </ul>
            </div>

            <div>
              <img style={{ width: '350px', height: '350px', borderRadius: '10%', margin: '10px' }} src='/MyPic.jpeg' alt='profile Pic' />
            </div></div>

          <div id='experience' style={{ marginLeft: '50px', marginTop: '150px' }}>
            <h1>My   experience</h1>
            <div style={{ borderTop: "2px solid black", width: '800px' }} ></div>

            <div style={{ display: 'flex' }}>

              <nav style={{
                display: 'flex', flexDirection: 'column', marginLeft: '20px', position: 'relative'
              }}>
                <div style={{ borderLeft: "2px solid black", height: "280px" }}></div>
              </nav>
              <ul>
                <li>Optival solutions</li>
                <p> Joined Optival Solution as a full-time Developer, working on building and maintaining robust web applications.
                  Primarily involved in backend development using Python and Django, with a focus on clean, scalable code.
                  Responsible for designing and implementing RESTful APIs for seamless communication between services.
                  Integrated MySQL for efficient data storage and retrieval, ensuring database optimization and security.
                  Utilized Redis for caching and improving application performance in high-load scenarios.
                  Collaborating with frontend developers using React to ensure smooth API consumption and user experience.
                  Participating in code reviews, debugging, and testing to maintain high code quality.
                  Contributed to automation scripts to streamline internal processes and reduce manual effort.
                  Worked in Agile development environments, attending daily stand-ups and sprint planning meetings.
                  Actively learning and adopting best practices in software architecture and system design.
                  Engaging with the team to solve technical challenges and deliver timely, effective solutions.</p>
              </ul></div>
          </div>

          <div id='experience' style={{ marginLeft: '50px', marginTop: '50px' }}>
            <h1>My Interview  experience</h1>
            <div style={{ borderTop: "2px solid black", width: '800px' }} ></div>

            <div style={{ display: 'flex' }}>

              <nav style={{
                display: 'flex', flexDirection: 'column', marginLeft: '20px', position: 'relative'
              }}>
                <div style={{ borderLeft: "2px solid black", height: "450px" }}></div>
              </nav>
              <ul>
                <li> Trainee Software Engineer at Optival Solutions (MEDPLUS)</li>
                <p><b>Interview Experience :</b> <br />Trainee Software Engineer at Optival Solutions (MEDPLUS)

                  The recruitment process for the Trainee Software Engineer role at Optival Solutions consisted of two rounds of written tests followed by two rounds of interviews.
                  <br /><b>
                    Written Tests:</b>
 <br />
In the first round, I successfully completed all questions, including a bonus question, covering aptitude, reasoning, pseudo code, and coding problems.
After clearing the first round, I progressed to the second written test, which included aptitude, reasoning, and coding questions focused on Data Structures and Algorithms (DSA). I answered all questions accurately and efficiently.
 <br /><b>Interview Rounds:</b><br /> In the first interview round, I entered confidently and greeted the two interviewers politely. After providing a clear and concise self-introduction, I answered questions about my personal background, including my family and education.
The interviewers then presented me with a coding problem that was a modified version of a question from the previous written test. After solving it, they gave me another coding challenge, which I also completed successfully.
I explained the entire flow and logic behind my solutions clearly. They further tested my problem-solving skills with a permutations and combinations question, which I answered confidently.
 In the second interview round, I faced questions on microprocessors, coding, and SQL, demonstrating my technical knowledge across multiple domains.
                  <br />
                  This comprehensive interview process helped me reinforce my technical skills and boosted my confidence in problem-solving under pressure.</p>
              </ul></div>
          </div>
          {/* <div style={{ borderTop: "2px solid black", width: "850px" }}> */}
          <div style={{ background: '#383232ff', width: '100%', textAlign: 'center', margin: '20px', height: '400px' }}>
            <div style={{ margin: '150px', width: '50%',padding:'100px' }}>
              <p1>Get in touch</p1><br />
              <h1>Let’s Work Together</h1>

              <p>I’m open for new opportunities – especially ambitious or large projects. However, my inbox is always open. Whether you have a question or just want to say hi, I’ll try my best to get back to you!.</p>
            <button title='send mail' style={{cursor:'pointer'}} onClick={()=>{window.open("mailto:saisankula369@gmail.com?subject=Greetings")}} >Say Hello</button>
            </div> </div>



        </div>
      </div>

      <div style={{ marginLeft: 'auto', justifyContent: 'flex-end' }}>
        <nav style={{
          display: 'flex', flexDirection: 'column', gap: '10px', right: '1px',
          position: 'fixed', marginBottom: 'auto', justifyContent: 'flex-end', height: '100vh'
        }}>
          <h1 style={{ writingMode: 'vertical-rl', fontSize: '15px', margin: 0, lineHeight: 1.2, position: 'relative', right: '17px' }}>saisankula369@gmail.com</h1>
          <div style={{ borderLeft: "2px solid " + String(fontColor), height: "115px", position: 'relative', right: '11px' }}></div>

        </nav>
      </div>





    </div>

  </div>);
}

export default App;
