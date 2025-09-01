const AboutItems = [
  {
    icon: "🎯",
    title: "Our Mission",
    description:
      "At JobTalks, our mission is to help professionals make informed career decisions by providing honest company reviews, job listings, and salary insights.",
  },
  {
    icon: "🌍",
    title: "Our Vision",
    description:
      "We aim to create a platform where job seekers can explore company cultures, compare salaries, and access real employee experiences to build successful careers.",
  },
  {
    icon: "🌟",
    title: "Honest Reviews",
    description:
      "Get real insights from employees to understand company culture, salaries, and growth opportunities.",
  },
  {
    icon: "💼",
    title: "Job Listings",
    description:
      "Explore top job openings from various industries and connect with hiring managers directly.",
  },
  {
    icon: "📈",
    title: "Career Insights",
    description:
      "Receive expert career advice, resume tips, and interview preparation strategies to excel in your journey.",
  },
];

const About = ({ aboutRef }) => {
  return (
    <section ref={aboutRef} className="bg-light py-5">
      <div className="container text-center py-5">
        <h1 className="fw-bold mb-5 text-primary">About</h1>
        <div className="row justify-content-center">
          {AboutItems.map((item, index) => (
            <div
              key={index}
              className="col-lg-4 col-md-6 col-sm-8 mb-4 d-flex align-items-stretch"
            >
              <div
                className="card p-4 shadow-sm text-center w-100 d-flex flex-column"
                style={{
                  borderRadius: "10px",
                  minHeight: "300px",
                  boxShadow: "0 4px 15px rgba(0, 0, 0, 0.1)",
                  transition: "transform 0.3s ease, box-shadow 0.3s ease",
                }}
                onMouseOver={(e) => {
                  e.currentTarget.style.transform = "scale(1.05)";
                  e.currentTarget.style.boxShadow =
                    "0 6px 20px rgba(0, 0, 0, 0.2)";
                }}
                onMouseOut={(e) => {
                  e.currentTarget.style.transform = "scale(1)";
                  e.currentTarget.style.boxShadow =
                    "0 4px 15px rgba(0, 0, 0, 0.1)";
                }}
              >
                <div className="fs-1 mb-3">{item.icon}</div>
                <h5 className="mt-2 fw-bold text-primary">{item.title}</h5>
                <p className="text-muted flex-grow-1">{item.description}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default About;