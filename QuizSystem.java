import java.util.Scanner;

class User {
    private String username;
    private String password;
    private String profileInfo;

    public User(String username, String password, String profileInfo) {
        this.username = username;
        this.password = password;
        this.profileInfo = profileInfo;
    }

    public String getUsername() {
        return username;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    public void updateProfile(String profileInfo) {
        this.profileInfo = profileInfo;
        System.out.println("Profile updated successfully.");
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Password changed successfully.");
    }

    public void showProfile() {
        System.out.println("Profile Information: " + profileInfo);
    }
}

class MCQ {
    private String question;
    private String[] options;
    private int correctAnswer;

    public MCQ(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public void displayQuestion() {
        System.out.println(question);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    public boolean checkAnswer(int answer) {
        return answer == correctAnswer + 1;
    }
}

public class QuizSystem {
    private static User currentUser;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        User user = new User("Nypunya", "1234", "Nypunya Profile");
        MCQ[] questions = {
            new MCQ("1. What is 4 * 3 * 2?", new String[]{"12", "24", "36", "48"}, 1),
            new MCQ("2. What is 12 * 12?", new String[]{"144", "132", "156", "120"}, 0),
            new MCQ("3. What is 19 - 13?", new String[]{"6", "7", "8", "9"}, 1),
            new MCQ("4. What is 18 % 5?", new String[]{"1", "2", "3", "4"}, 2),
            new MCQ("5. What is 12 + 5 - 2?", new String[]{"15", "16", "17", "18"}, 1)
        };

        login(user);

        boolean sessionActive = true;
        while (sessionActive) {
            System.out.println("\nMenu:");
            System.out.println("1. Update Profile");
            System.out.println("2. Change Password");
            System.out.println("3. Take Quiz");
            System.out.println("4. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    updateProfile();
                    break;
                case 2:
                    changePassword();
                    break;
                case 3:
                    takeQuiz(questions);
                    break;
                case 4:
                    sessionActive = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
        System.out.println("Session closed. Goodbye!");
    }

    private static void login(User user) {
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (username.equals(user.getUsername()) && user.validatePassword(password)) {
                currentUser = user;
                loggedIn = true;
                System.out.println("Login successful. Welcome, " + user.getUsername() + "!");
            } else {
                System.out.println("Invalid credentials, please try again.");
            }
        }
    }

    private static void updateProfile() {
        System.out.print("Enter new profile information: ");
        String newProfileInfo = scanner.nextLine();
        currentUser.updateProfile(newProfileInfo);
    }

    private static void changePassword() {
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        currentUser.changePassword(newPassword);
    }

    private static void takeQuiz(MCQ[] questions) {
        int score = 0;
        int questionCount = questions.length;

        long quizStartTime = System.currentTimeMillis();
        System.out.println("Quiz started! You have 30 seconds in total.");

        for (int i = 0; i < questionCount; i++) {
            if (System.currentTimeMillis() - quizStartTime > 30000) {
                System.out.println("Time's up for the entire quiz!");
                break;
            }

            MCQ question = questions[i];
            question.displayQuestion();

            boolean answered = false;

            while (System.currentTimeMillis() - quizStartTime < 30000 && !answered) {
                if (scanner.hasNextInt()) {
                    int answer = scanner.nextInt();
                    scanner.nextLine();
                    answered = true;

                    if (question.checkAnswer(answer)) {
                        score++;
                        System.out.println("Correct answer!");
                    } else {
                        System.out.println("Wrong answer!");
                    }
                }

                if (System.currentTimeMillis() - quizStartTime > 30000) {
                    System.out.println("Time's up for the entire quiz!");
                    break;
                }
            }

            if (!answered) {
                System.out.println("Time's up for this question or no answer provided! Moving to the next question.");
            }

            if (System.currentTimeMillis() - quizStartTime > 30000) {
                System.out.println("Time's up for the entire quiz!");
                break;
            }
        }

        System.out.println("Quiz ended. Your score: " + score + "/" + questionCount);
    }
}
