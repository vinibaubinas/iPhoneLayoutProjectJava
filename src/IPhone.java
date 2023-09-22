import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class IPhone extends JFrame {
    private boolean isPlaying;
    private boolean isOnCall;
    private List<String> playlist;
    private String currentTrack;
    private String phoneNumber;
    private String browsingHistory;

    private JButton playButton;
    private JButton pauseButton;
    private JButton stopButton;
    private JButton nextButton;
    private JButton previousButton;
    private JButton callButton;
    private JButton textButton;
    private JButton browseButton;
    private JTextArea displayArea;

    public IPhone() {
        isPlaying = false;
        isOnCall = false;
        playlist = new ArrayList<>();
        currentTrack = "";
        phoneNumber = "";
        browsingHistory = "";

        setTitle("IPhone Simulator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));

        playButton = new JButton("Play");
        pauseButton = new JButton("Pause");
        stopButton = new JButton("Stop");
        nextButton = new JButton("Next");
        previousButton = new JButton("Previous");
        callButton = new JButton("Call");
        textButton = new JButton("Text");
        browseButton = new JButton("Browse");

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                play();
                display("Playing: " + getCurrentTrack());
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pause();
                display("Paused: " + getCurrentTrack());
            }
        });

        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stop();
                display("Stopped: " + getCurrentTrack());
            }
        });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextTrack();
                display("Next track: " + getCurrentTrack());
            }
        });

        previousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                previousTrack();
                display("Previous track: " + getCurrentTrack());
            }
        });

        callButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                makeCall("123-456-789");
                display("Calling 123-456-789");
            }
        });

        textButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendTextMessage("Friend", "Hello!");
                display("Sending message to Friend: Hello!");
            }
        });

        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                navigateToURL("https://www.example.com");
                display("Navigating to https://www.example.com");
            }
        });

        buttonPanel.add(playButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(previousButton);
        buttonPanel.add(callButton);
        buttonPanel.add(textButton);
        buttonPanel.add(browseButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        add(displayArea, BorderLayout.SOUTH);
    }

    private void display(String message) {
        displayArea.append(message + "\n");
    }

    public void play() {
        if (!playlist.isEmpty() && !isPlaying) {
            isPlaying = true;
        } else if (isPlaying) {
            display("Already playing: " + currentTrack);
        } else {
            display("Playlist is empty. Add songs.");
        }
    }

    public void pause() {
        if (isPlaying) {
            isPlaying = false;
        } else {
            display("Not playing.");
        }
    }

    public void stop() {
        if (isPlaying) {
            isPlaying = false;
        } else {
            display("Not playing.");
        }
    }

    public void nextTrack() {
        if (!playlist.isEmpty() && isPlaying) {
            int currentIndex = playlist.indexOf(currentTrack);
            if (currentIndex < playlist.size() - 1) {
                currentIndex++;
                currentTrack = playlist.get(currentIndex);
            } else {
                display("End of playlist.");
            }
        } else if (!isPlaying) {
            display("Not playing.");
        } else {
            display("Playlist is empty.");
        }
    }

    public void previousTrack() {
        if (!playlist.isEmpty() && isPlaying) {
            int currentIndex = playlist.indexOf(currentTrack);
            if (currentIndex > 0) {
                currentIndex--;
                currentTrack = playlist.get(currentIndex);
            } else {
                display("Beginning of playlist.");
            }
        } else if (!isPlaying) {
            display("Not playing.");
        } else {
            display("Playlist is empty.");
        }
    }

    public void addMusic(String music) {
        playlist.add(music);
        display("Added music: " + music);
    }

    public void makeCall(String number) {
        if (!isOnCall) {
            phoneNumber = number;
            isOnCall = true;
        } else {
            display("Already on a call.");
        }
    }

    public void sendTextMessage(String recipient, String message) {
        if (!isOnCall) {
            display("Sending message to " + recipient + ": " + message);
        } else {
            display("Cannot send message while on a call.");
        }
    }

    public void navigateToURL(String url) {
        if (!isOnCall) {
            browsingHistory += "> " + url + "\n";
        } else {
            display("Cannot browse while on a call.");
        }
    }

    public String getCurrentTrack() {
        return currentTrack;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                IPhone iphone = new IPhone();
                iphone.setVisible(true);
            }
        });
    }
}
