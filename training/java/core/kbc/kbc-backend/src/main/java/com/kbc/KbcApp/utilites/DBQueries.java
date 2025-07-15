package com.kbc.KbcApp.utilites;

public class DBQueries {
	private DBQueries() {
		
	}
	//User Queries
	public static final String INSERT_USER="INSERT into tbl_users(Username, Password, CreatedAt) VALUES(:username,:password,:createdAt)";
	public static final String GET_USER="SELECT UserId, Username, Password, Role, Status, CreatedAt, ModifiedAt,ModifiedBy FROM tbl_users WHERE (:userIdFlag=0 or UserId=:userId) and (:usernameFlag=0 or Username=:username) and (:passwordFlag=0 or Password=:password)";
	public static final String INSERT_USER_LOG="INSERT into tbl_users_log(UserId, Username, Password, Role, Status, CreatedAt, ModifiedAt, ModifiedBy) VALUES(:userId, :username, :password, :role, :status, :createdAt, :modifiedAt, :modifiedBy)";
	

	//Question Queries
	public static final String INSERT_QUESTION="insert into tbl_questions(Question,Option1,Option2,Option3,Option4 ,Level,Status,CreatedAt,CreatedBy) values (?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE_QUESTION="update tbl_questions set Question=?, Option1 =?,Option2=?,Option3=?,Option4=?,Level=?,Status=?,ModifiedBy=?, ModifiedAt=? where QuestionId=?";
	public static final String INSERT_QUESTION_TO_LOG="insert into tbl_questions_log(QuestionId,Question,Option1,Option2,Option3,Option4 ,Level,Status,CreatedAt, CreatedBy, ModifiedBy, ModifiedAt) values (?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String GET_QUESTION="select QuestionId,Question,Option1,Option2,Option3,Option4 ,Level,Status,CreatedAt, CreatedBy, ModifiedAt,ModifiedBy from tbl_questions where(:questionIdFlag = 0 or QuestionId=:questionId) and (:statusFlag =0  or Status=:status) and (:levelFlag = 0 or Level=:level)";
	
	//Game Queries
	
	public static final String INSERT_GAME="INSERT into tbl_game(UserId, NumOfQuestions, Score,PlayedAt) VALUES(:userId,:numOfQuestions,:score,:playedAt)";
	public static final String GET_ALL_GAMES="SELECT GameId, UserId, NumOfQuestions, Score, PlayedAt FROM tbl_game where (:userIdFlag=0 or UserId=:userId)";
	public static final String GET_TOTAL_GAMES_OF_USER="SELECT COUNT(GameId) from tbl_game where UserId=:userId";
	public static final String GET_SUM_OF_SCORE_PERCENTAGE_BY_USER_ID="SELECT (SUM(Score/NumOfQuestions)*100) AS TotalScore from tbl_game where UserId=:userId";

	//Game Config Queries
	public static final String INSERT_GAME_CONFIGURATION = "INSERT into tbl_game_configuration(NoQuestions, TimeAllocated, ModifiedAt, ModifiedBy) VALUES (?,?,?,?)";
	public static final String UPDATE_GAME_CONFIGURATION = "UPDATE tbl_game_configuration SET NoQuestions=?, TimeAllocated=? , ModifiedAt = ?, ModifiedBy = ?";
	public static final String READ_GAME_CONFIGURATION = "SELECT NoQuestions, TimeAllocated, ModifiedAt, ModifiedBy, HeadSysTime from tbl_game_configuration";
	public static final String GET_TIME_AND_QUESTIONS = "SELECT NoQuestions,TimeAllocated from tbl_game_configuration";
	public static final String INSERT_GAME_CONFIGURATION_LOG = "INSERT into tbl_game_configuration_log(NoQuestions, TimeAllocated , ModifiedAt ,ModifiedBy) VALUES(?,?,?,?)"; 
}