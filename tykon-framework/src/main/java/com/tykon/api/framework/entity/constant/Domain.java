package com.tykon.api.framework.entity.constant;

public class Domain {
	
	public enum MessageKind{
        Normal,
        Scheduled,
        Cab,
        Daycare,
        CabInOut,
        cabPickDrop,
        fee_reminder,
        FeeGenerated,
        FeePaid,
        Attendance,
        dailyReport,
        BirthdayReminder,
        KidNotFound,
        Suggestions,
        Helpline,
        HPFreeTrial,
        SchoolInquiry,
        Feedback
    }

    public enum MessageType{
        //Admin -> Group
    	SentToGroup("Custom") ,

        //Parent -> Admin
        SentToAdmin("SentToAdmin"),


        //Admin -> Teachers + Students
        All("All Teachers and Students") ,

        //Admin -> Students
        AllStudents("All Students"),

        //Admin -> Teachers
        AllTeachers("All Teachers"),

        //HelloParent -> Anyone
        HelloParent("Team HelloParent");
    	
        private String displayName;

        MessageType(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }
    }

    public enum EventRepeatType{
    	None("Do not repeat"),
        Weekly("Weekly"),
        Monthly("Monthly"),
        Quarterly("Every 3 Months"),
        Biannually("Every 6 months"),
        Yearly("Yearly");
    	
        private String displayName;

    	EventRepeatType(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }
    }

    public enum ReceiverType{
        Student,
        User
    }

    public enum PushReceiverType{
        Parent,
        Teacher
    }

    public enum LeadStatus{
        New("New"),
        Inprogress("In Progress"),
        Junk("Junk"),
        Lost("Lost"),
        Won("Won");
    	
        private String displayName;

        LeadStatus(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }

    }

    public enum PictureApprovalStatus{
        Pending,
        Approved,
        Rejected
    }

    public enum ApprovalRequestType{
        Student,
        Father,
        Mother;
    }

    public enum BlogStatus{
        Draft,
        Publish,
        Public;
    }

    public enum BlogType{
    	HelloParent("Hello Parent"),
        School("School"),
        User("User");
    	
    	
        private String displayName;

        BlogType(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }

    }

    public enum Periodicity{
        Monthly("Monthly"),
        Yearly("Yearly"),
        OneTime("One Time");
    	
        private String displayName;

        Periodicity(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }
    }

    public enum FeeStatus{
    	PendingApproval("Pending Approval"),
        Approved("Approved"),
        Paid("Paid"),
        PartialPaid("Partial Paid"),
        Cancelled("Cancelled");
    	
        private String displayName;

        FeeStatus(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }

    }

    public enum AmountMode{
        Cash("Cash"),
        BankDraft("Bank Transfer"),
        Cheque("Cheque"),
        OnlinePayment("Online Payment"),
        CardPayment("Card Payment"),
        IMPS("IMPS");
    	
        private String displayName;

        AmountMode(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }

    }

    public enum TransactionStatus{
        Pending,
        Completed,
        Cancelled
    }

    public enum ReportType{
        Monthly,
        Weekly,
        Daily
    }

    public enum AlbumVisibility{
    	Public("Public- Visible to Everyone"),
        Private("Private- Visible to only School Parents");
    	
        private String displayName;

        AlbumVisibility(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }

    }

    public enum TagType{
        Age,
        Topic
    }

    public enum ForumSortBy{
        Newest,
        Oldest,
        Popular,
        LatestReplied,
        MostReplied
    }

    public enum ForumSortByDateRange{
        Today,
        ThisWeek,
        ThisMonth,
        ThisYear,
        AllTime,
    }

    public enum TransactionItemType{
        Fee
    }

    public enum NotificationLogStatus{
        Pending,
        Success,
        Failure
    }

    public enum NotificationDeviceType{
        Android,
        Apple
    }

    public enum StudentFeeFrequency{
        Monthly,
        Quarterly,
        HalfYearly,
        Annually
    }

    public enum LinkType{
        YouTube,
        Other,
        Video
    }

    public enum NewMessageType {
       
    }

    public enum DayCareMenuType{
        Breakfast,
        Lunch,
        Snacks
    }

    public enum BlogSortBy{
        MostViewed,
        MostComments,
        MostLiked,
        Recent
    }

    public enum SubscriptionPlan{
        Premium,
        Basic,
        Standard,
        Advanced
    }

    public enum UserType{
        HPCreated,
        Free
    }

    public enum TeacherActivityType{
    	ClassTeacher("Class Teacher"),

        SubjectTeacher("Subject Teacher");
    	
        private String displayName;

        TeacherActivityType(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }

    }

    public enum ActivityType{
        ClassWork,
        HomeWork
    }

    public enum InvoiceType{
        Annually("Annually"),
        BiAnnually("Bi-Annually"),
        Quarterly("Quarterly");
    	
        private String displayName;

        InvoiceType(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }

    }

    public enum AuthorizedPersonApprovalType{
        Add,
        Delete,
        Update
    }

    public enum PostType{
        Post,
        Poll
    }

    public enum MediaType{
        Image,
        Video
    }

    public enum SMSType{
        Transactional,
        Marketing
    }

    public enum LateFeeType{
        Fixed,
        Daily,
        Weekly,
        Monthly
    }

    public enum SchoolType{
        PreSchool,
        K12,
        Coaching
    }
    public enum ContentCardType {
         BLOG,
         FORUM, 
         TIMELINE, 
         STATIC,
         URL
    }

    public enum TeacherContentCardType
    {
       
        STATIC,
        URL
    }

    public enum CardFor {
        Teachers,
        Parents
    }
    
    public enum TripType{
        Pick,
        Drop,
        Absent
    }
}
