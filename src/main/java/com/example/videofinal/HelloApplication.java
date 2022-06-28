package com.example.videofinal;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.io.FilenameUtils;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import javax.imageio.ImageIO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Comparator;
import static java.lang.Double.parseDouble;
public class HelloApplication extends Application {
    Pane pn;
    boolean p;
    private MediaPlayer mediaPlayer;
    private Slider progressSlider;
    private ChangeListener<Duration> progressListener;
   static ArrayList<String> samples;
  static ArrayList<String> tempSamples;
    ArrayList<String>temp;

    FrameInfo frameInfo;
    @Override
    public void start(Stage stage) throws IOException, Exception {
        samples=new ArrayList<>();
        temp=new ArrayList<>();
        tempSamples=new ArrayList<>();
        Pane startPn=new Pane();
        ImageView image=new ImageView();
        image.setImage(new Image("C:\\Users\\Lana\\Desktop\\352-C01-Editing-Primary.png"));
        image.setFitWidth(1024);
        image.setFitHeight(800);
        startPn.getChildren().add(image);
        Scene startScene=new Scene(startPn, 1024, 800);
        stage.setScene(startScene);
       ///////////////////////////////////////////////////////start scene////////////////////////////////////////
        Button videoEditing=new Button("Edit Video");
        videoEditing.setPrefWidth(200);
        videoEditing.setPrefHeight(50);
        videoEditing.setLayoutX(400);
        videoEditing.setLayoutY(300);
        ////////////////////video editing///////////////////////////////////////////////
        videoEditing.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File mediaFile =fileChooser.showOpenDialog(stage);
            Media media = new Media(mediaFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            MediaView mediaView1 = new MediaView(mediaPlayer);
            MediaView mediaView2 = new MediaView(mediaPlayer);
            MediaView mediaView3 = new MediaView(mediaPlayer);
            MediaView mediaView4 = new MediaView(mediaPlayer);
            MediaView mediaView5 = new MediaView(mediaPlayer);
            MediaView mediaView6 = new MediaView(mediaPlayer);
            MediaView mediaView7 = new MediaView(mediaPlayer);
            mediaView1.setLayoutX(0);
            mediaView1.setFitWidth(1024);
            mediaView1.setFitHeight(700);
            mediaView2.setLayoutX(0);
            mediaView2.setFitWidth(1024);
            mediaView2.setFitHeight(700);
            mediaView3.setLayoutX(0);
            mediaView3.setFitWidth(1024);
            mediaView3.setFitHeight(700);
            mediaView4.setLayoutX(0);
            mediaView4.setFitWidth(1024);
            mediaView4.setFitHeight(700);
            mediaView5.setLayoutX(0);
            mediaView5.setFitWidth(1024);
            mediaView5.setFitHeight(700);
            mediaView6.setLayoutX(0);
            mediaView6.setFitWidth(1024);
            mediaView6.setFitHeight(700);
            mediaView7.setLayoutX(0);
            mediaView7.setFitWidth(1024);
            mediaView7.setFitHeight(700);
            p=false;
            pn=new Pane();
            Pane cutpn=new Pane();
            Pane deletepn=new Pane();
            Pane replacepn=new Pane();
            Pane textwmpn=new Pane();
            Pane imgwmpn=new Pane();
            Pane WH=new Pane();
            Pane videoCutPn=new Pane();
            Scene scene = new Scene(pn, 1024, 800);
            Scene textwmScene = new Scene(textwmpn, 1024, 800);
            Scene cutScene=new Scene(cutpn,1024,800);
            Scene deleteScene=new Scene(deletepn,1024,800);
            Scene replaceScene=new Scene(replacepn,1024,800);
            Scene imgwmScene=new Scene(imgwmpn,1024,800);
           Scene videoCut=new Scene(videoCutPn,1024,800);
            Scene WHScene=new Scene(WH,1024,800);
            textwmpn.getChildren().add(mediaView5);
            pn.getChildren().add(mediaView1);
            cutpn.getChildren().add(mediaView2);
            deletepn.getChildren().add(mediaView3);
            imgwmpn.getChildren().add(mediaView6);
            replacepn.getChildren().add(mediaView4);
            WH.getChildren().add(mediaView7);
            // allows the user to see the progress of the video playing
            progressSlider = createSlider(scene);
            pn.getChildren().add(progressSlider);
            // update slider as video is progressing (later removal)
            progressListener = (ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) -> {
                progressSlider.setValue(newValue.toSeconds());
            };
            // as the media is playing move the slider for progress
            mediaPlayer.currentTimeProperty().addListener(progressListener);
            try {
                frameInfo = convertMovietoJPG(mediaFile.toString(), "C:\\Users\\Lana\\Desktop\\Images","jpg",0,0);
            } catch (Exception e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mediaView1.setOnMouseClicked(event1 -> {
                if(!p)
                {
                    mediaPlayer.play();
                    p=true;
                }
                else {
                    mediaPlayer.stop();
                    p=false;
                }
            });
            mediaView2.setOnMouseClicked(event2 -> {
                if(!p)
                {
                    mediaPlayer.play();
                    p=true;
                }
                else {
                    mediaPlayer.stop();
                    p=false;
                }
            });
            mediaView3.setOnMouseClicked(event3 -> {
                if(!p)
                {
                    mediaPlayer.play();
                    p=true;
                }
                else {
                    mediaPlayer.stop();
                    p=false;
                }
            });
            File[] files = new File("C:/Users/Lana/Desktop/Images").listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    samples.add(FilenameUtils.removeExtension(file.getName())) ;
                }
            }
            Collections.sort(samples, new MyComparator());
            tempSamples.addAll(samples);
            Collections.sort(tempSamples, new MyComparator());
            if(!samples.isEmpty())
            {
                int j=0;
                for(int i=0;i<samples.size();i++)
                {

                    String imgname =samples.get(i);

                    ImageView imgv=new ImageView();
                    imgv.setImage(new Image("C:\\Users\\Lana\\Desktop\\Images\\"+imgname+".jpg"));
                    imgv.setFitWidth((int)(1024/frameInfo.framesNum));
                    imgv.setFitHeight(100);
                    imgv.setLayoutX(j);
                    imgv.setLayoutY(600);
                    pn.getChildren().add(imgv);
                    j=j+((int)(1024/frameInfo.framesNum));

                }
            }
            stage.setScene(scene);
            ////////////////////////delete , cut , replace ////////////////////////////////////////
            ////////////////////////////////start cut input/////////////////////////
            NumFieldFX startCut=new NumFieldFX();
            startCut.setTranslateX(70);
            startCut.setTranslateY(600);
            cutpn.getChildren().add(startCut);
            Label label1 = new Label("Start Cut:");
            label1.setLayoutX(70);
            label1.setLayoutY(580);
            label1.setPrefHeight(10);
            cutpn.getChildren().add(label1);
            ////////////////////////////////////////////////////////////////////////
            /////////////////////end cut input //////////////////////////
            NumFieldFX endCut=new NumFieldFX();
            endCut.setLayoutX(70);
            endCut.setLayoutY(650);
            cutpn.getChildren().add(endCut);
            Label label2 = new Label("End Cut:");
            label2.setLayoutX(70);
            label2.setLayoutY(630);
            label2.setPrefHeight(10);
            cutpn.getChildren().add(label2);
            /////////////////////////////////////////////////////////
////////////////////////submit cut button///////////////////////////
            Button submitCut = new Button("Cut");
            submitCut.setLayoutX(0);
            submitCut.setLayoutY(600);
            submitCut.setPrefWidth(50);
            submitCut.setOnAction(event4 -> {
                double start= parseDouble(startCut.getText());
                double end = parseDouble(endCut.getText());
                int startFrameNum=(int)(start*frameInfo.framesRate);
                int endFrameNum=(int)(end*frameInfo.framesRate);
                int j=tempSamples.indexOf(String.valueOf(startFrameNum));
                for(int i=0;i<j;i++)
                {
                    tempSamples.remove(0);
                }
                j= (tempSamples.indexOf(String.valueOf(endFrameNum)))+1;
                int k=tempSamples.size()-j;

                for(int i=0;i<k;i++)
                {
                    tempSamples.remove(j);
                }

                convertJPGtoMovie("C:\\Users\\Lana\\Desktop\\Images\\",tempSamples, "C:\\Users\\Lana\\Desktop\\VideoEditorTemp\\cut.mp4",frameInfo.framesRate);
                stage.setScene(scene);
            });
            cutpn.getChildren().add(submitCut);
            /////////////////////////////////////////
/////////////////////////cut button/////////////////////
            Button cut = new Button("Cut");
            cut.setPrefWidth(50);
            cut.setTranslateX(500);
            cut.setTranslateY(500);
            cut.setOnAction(event5 -> {
                stage.setScene(cutScene);
            });
            pn.getChildren().add(cut);
            //////////////////////////////////////
//////////////////////delete button////////////////////
            Button delete = new Button("Delete");

            delete.setPrefWidth(50);
            delete.setTranslateX(550);
            delete.setTranslateY(500);
            delete.setOnAction(event6 -> {
                stage.setScene(deleteScene);
            });
            pn.getChildren().add(delete);
            //////////////////////////////////////////////////////
            //////////////////////replace button////////////////////
            Button replace = new Button("Replace");

            replace.setPrefWidth(70);
            replace.setTranslateX(600);
            replace.setTranslateY(500);
            replace.setOnAction(event7 -> {
                stage.setScene(replaceScene);
            });
            pn.getChildren().add(replace);
            //////////////////////////////////////////////////////
            ////////////////////////////////start replace input/////////////////////////
            NumFieldFX startReplace=new NumFieldFX();
            startReplace.setTranslateX(70);
            startReplace.setTranslateY(600);
            replacepn.getChildren().add(startReplace);
            Label label7 = new Label("Start Replace:");
            label7.setLayoutX(70);
            label7.setLayoutY(580);
            label7.setPrefHeight(10);
            replacepn.getChildren().add(label7);
            ////////////////////////////////////////////////////////////////////////
            /////////////////////end replace input //////////////////////////
            NumFieldFX endReplace=new NumFieldFX();
            endReplace.setLayoutX(70);
            endReplace.setLayoutY(650);
            replacepn.getChildren().add(endReplace);
            Label label8 = new Label("End Replace:");
            label8.setLayoutX(70);
            label8.setLayoutY(630);
            label8.setPrefHeight(10);
            replacepn.getChildren().add(label8);
            /////////////////////////////////////////////////////////////
            /////////////////////new replace input //////////////////////////
            NumFieldFX newReplace=new NumFieldFX();
            newReplace.setLayoutX(200);
            newReplace.setLayoutY(600);
            replacepn.getChildren().add(newReplace);
            Label label9 = new Label("Where to put selected frames/sec/:");
            label9.setLayoutX(200);
            label9.setLayoutY(580);
            label9.setPrefHeight(10);
            replacepn.getChildren().add(label9);
            /////////////////////////////////////////////////////////////
            ////////////////////////submit replace button///////////////////////////
            Button submitReplace = new Button("Replace");
            submitReplace.setLayoutX(0);
            submitReplace.setLayoutY(600);
            submitReplace.setPrefWidth(70);
            submitReplace.setOnAction(event8 -> {
                double start= parseDouble(startReplace.getText());
                double end = parseDouble(endReplace.getText());
                double newRep=parseDouble(newReplace.getText());
                int startFrameNum=(int)(start*frameInfo.framesRate);
                int endFrameNum=(int)(end*frameInfo.framesRate);
                int newFrameNum=(int)(newRep*frameInfo.framesRate);
                int j=tempSamples.indexOf(String.valueOf(startFrameNum));

                int k=endFrameNum-startFrameNum;
                for(int i=0;i<=k;i++)
                {
                    temp.add(tempSamples.get(j));
                    tempSamples.remove(j);
                }
                int jj=tempSamples.indexOf(String.valueOf(newFrameNum));
                for(int i=0;i<=k;i++)
                {
                    tempSamples.remove(jj);
                    tempSamples.add(jj,temp.get(i));
                    jj++;
                }
                convertJPGtoMovie("C:\\Users\\Lana\\Desktop\\Images\\",tempSamples, "C:\\Users\\Lana\\Desktop\\VideoEditorTemp\\replace.mp4",frameInfo.framesRate);

                stage.setScene(scene);
            });
            replacepn.getChildren().add(submitReplace);
            /////////////////////////////////////////
            ////////////////////////////////start delete input/////////////////////////
            NumFieldFX startDelete=new NumFieldFX();
            startDelete.setTranslateX(70);
            startDelete.setTranslateY(600);
            deletepn.getChildren().add(startDelete);
            Label label4 = new Label("Start Delete:");
            label4.setLayoutX(70);
            label4.setLayoutY(580);
            label4.setPrefHeight(10);
            deletepn.getChildren().add(label4);
            ////////////////////////////////////////////////////////////////////////

            /////////////////////end delete input //////////////////////////
            NumFieldFX endDelete=new NumFieldFX();

            endDelete.setLayoutX(70);
            endDelete.setLayoutY(650);
            deletepn.getChildren().add(endDelete);
            Label label5 = new Label("End Delete:");
            label5.setLayoutX(70);
            label5.setLayoutY(630);
            label5.setPrefHeight(10);
            deletepn.getChildren().add(label5);
            /////////////////////////////////////////////////////////////

////////////////////////submit delete button///////////////////////////
            Button submitDelete = new Button("Delete");
            submitDelete.setLayoutX(0);
            submitDelete.setLayoutY(600);
            submitDelete.setPrefWidth(50);
            submitDelete.setOnAction(event9 -> {
                double start= parseDouble(startDelete.getText());
                double end = parseDouble(endDelete.getText());
                int startFrameNum=(int)(start*frameInfo.framesRate);
                int endFrameNum=(int)(end*frameInfo.framesRate);
                int j=tempSamples.indexOf(String.valueOf(startFrameNum));
                int k=endFrameNum-startFrameNum;
                for(int i=0;i<=k;i++)
                {
                    tempSamples.remove(j);
                }

                convertJPGtoMovie("C:\\Users\\Lana\\Desktop\\Images\\",tempSamples, "C:\\Users\\Lana\\Desktop\\VideoEditorTemp\\delete.mp4",frameInfo.framesRate);
                stage.setScene(scene);
            });
            deletepn.getChildren().add(submitDelete);
            //////////////////////////////////////////end of delete,cut,replace////////////////////////////////////
            //////////////////watermark/////////////////////////////
            //////////////////////////////text watermark///////////////////////////////////
            Button textwm = new Button("Text WM");
            textwm.setPrefWidth(70);
            textwm.setTranslateX(500);
            textwm.setTranslateY(530);
            textwm.setOnAction(event10 -> {
                stage.setScene(textwmScene);
            });
            pn.getChildren().add(textwm);
            TextField text=new TextField();
            text.setTranslateX(110);
            text.setTranslateY(600);
            textwmpn.getChildren().add(text);
            Label label11 = new Label("Text WaterMark:");
            label11.setLayoutX(110);
            label11.setLayoutY(580);
            label11.setPrefHeight(10);
            textwmpn.getChildren().add(label11);
            NumFieldFX textAlpha=new NumFieldFX();
            textAlpha.setTranslateX(110);
            textAlpha.setTranslateY(670);
            textwmpn.getChildren().add(textAlpha);
            Label label13 = new Label("Text Alpha:");
            label13.setLayoutX(110);
            label13.setLayoutY(650);
            label13.setPrefHeight(10);
            textwmpn.getChildren().add(label13);
            Button submittext = new Button("Add Text");
            submittext.setLayoutX(0);
            submittext.setLayoutY(600);
            submittext.setPrefWidth(100);
            submittext.setOnAction(event11 -> {
                addTextWatermark(text.getText(),tempSamples,"C:\\Users\\Lana\\Desktop\\textwm",Float.parseFloat(textAlpha.getText()),frameInfo.framesRate);
                stage.setScene(scene);
            });

            textwmpn.getChildren().add(submittext);
            stage.setScene(scene);
            /////////////end text waterMark//////////////////////
            ///////////////image waterMark//////////////////////////
            Button imgwm = new Button("Image WM");
            imgwm.setPrefWidth(100);
            imgwm.setTranslateX(570);
            imgwm.setTranslateY(530);
            imgwm.setOnAction(event12 -> {
                stage.setScene(imgwmScene);
            });
            pn.getChildren().add(imgwm);
            TextField img=new TextField();
            img.setTranslateX(110);
            img.setTranslateY(600);
            imgwmpn.getChildren().add(img);
            Label label12 = new Label("Image WaterMark , C:\\path\\imgname.ext");
            label12.setLayoutX(70);
            label12.setLayoutY(580);
            label12.setPrefHeight(10);
            imgwmpn.getChildren().add(label12);
            NumFieldFX imgAlpha=new NumFieldFX();
            imgAlpha.setTranslateX(110);
            imgAlpha.setTranslateY(670);
            imgwmpn.getChildren().add(imgAlpha);
            Label label14 = new Label("Image Alpha:");
            label14.setLayoutX(110);
            label14.setLayoutY(650);
            label14.setPrefHeight(10);
            imgwmpn.getChildren().add(label14);
            Button submitimg = new Button("Add Image");
            submitimg.setLayoutX(0);
            submitimg.setLayoutY(600);
            submitimg.setPrefWidth(100);
            submitimg.setOnAction(event13 -> {
                addImageWatermark(img.getText(),tempSamples,"C:\\Users\\Lana\\Desktop\\imagewm",Float.parseFloat(imgAlpha.getText()),frameInfo.framesRate);
                stage.setScene(scene);
            });
            imgwmpn.getChildren().add(submitimg);
            /////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////// save video ////////////////////////////////////
            TextField videoPath=new TextField();
            videoPath.setLayoutX(730);
            videoPath.setLayoutY(530);
            pn.getChildren().add(videoPath);
            Button save=new Button("save");
            save.setPrefWidth(50);
            save.setLayoutX(680);
            save.setLayoutY(530);
            save.setOnAction(event14 -> {
                convertJPGtoMovie("C:\\Users\\Lana\\Desktop\\Images\\",tempSamples, videoPath.getText(),frameInfo.framesRate);
            });
            pn.getChildren().add(save);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////edit frames rate///////////////////////////////////////////////////////
            NumFieldFX frameRate=new NumFieldFX();
            frameRate.setLayoutX(720);
            frameRate.setLayoutY(500);
            pn.getChildren().add(frameRate);
            Button rate=new Button("rate");
            rate.setPrefWidth(50);
            rate.setLayoutX(670);
            rate.setLayoutY(500);

            rate.setOnAction(event15 -> {
                frameInfo.framesRate=Double.parseDouble(frameRate.getText());
                convertJPGtoMovie("C:\\Users\\Lana\\Desktop\\Images\\",tempSamples,"C:\\Users\\Lana\\Desktop\\VideoEditorTemp\\rate.mp4",frameInfo.framesRate);
            });
            pn.getChildren().add(rate);

            ////////////////////////////////edit width and height//////////////////////////////////////
            Button editWH=new Button("Width & Height");
            editWH.setPrefWidth(150);
            editWH.setTranslateX(350);
            editWH.setTranslateY(530);
            editWH.setOnAction(event6 -> {
                stage.setScene(WHScene);
            });
            pn.getChildren().add(editWH);
            NumFieldFX newWidth=new NumFieldFX();
            newWidth.setTranslateX(70);
            newWidth.setTranslateY(600);
            WH.getChildren().add(newWidth);
            Label label15 = new Label("Width:");
            label15.setLayoutX(70);
            label15.setLayoutY(580);
            label15.setPrefHeight(10);
            WH.getChildren().add(label15);
            NumFieldFX newHeight=new NumFieldFX();
            newHeight.setLayoutX(70);
            newHeight.setLayoutY(650);
            WH.getChildren().add(newHeight);
            Label label16 = new Label("Height:");
            label16.setLayoutX(70);
            label16.setLayoutY(630);
            label16.setPrefHeight(10);
            WH.getChildren().add(label16);
            Button submitEditWH = new Button("Edit");
            WH.getChildren().add(submitEditWH);
            submitEditWH.setLayoutX(0);
            submitEditWH.setLayoutY(600);
            submitEditWH.setPrefWidth(50);
            submitEditWH.setOnAction(event20 -> {
                for(int i=0;i<tempSamples.size();i++)
                {
                    File inputFile = new File("C:/Users/Lana/Desktop/Images/"+tempSamples.get(i)+".jpg");
                    BufferedImage inputImage = null;
                    try {
                        inputImage = ImageIO.read(inputFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    BufferedImage outputImage = new BufferedImage(Integer.parseInt(newWidth.getText()),
                            Integer.parseInt(newHeight.getText()), inputImage.getType());
                    Graphics2D g2d = outputImage.createGraphics();
                    g2d.drawImage(inputImage, 0, 0, Integer.parseInt(newWidth.getText()), Integer.parseInt(newHeight.getText()), null);
                    g2d.dispose();
                    String path = "C:\\Users\\Lana\\Desktop\\Images\\"+File.separator+tempSamples.get(i)+".jpg";
                    // writes to output file
                    try {
                        ImageIO.write(outputImage, "jpg", new File(path));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                convertJPGtoMovie("C:\\Users\\Lana\\Desktop\\Images\\",tempSamples,"C:\\Users\\Lana\\Desktop\\VideoEditorTemp\\WH.mp4",frameInfo.framesRate);
                stage.setScene(scene);
            });

        });
        startPn.getChildren().add(videoEditing);
/////////////////////////////////////////////////////end of video editing////////////////////////////////////////////////////////////////////
        //////////////////////////////////create video///////////////////////////////////
        Button create=new Button("Create Video");

        create.setPrefWidth(200);
        create.setPrefHeight(50);
        create.setLayoutX(400);
        create.setLayoutY(350);
        create.setOnAction(event->{
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(stage);
            if(selectedDirectory != null){
                File[] files = new File(selectedDirectory.getAbsolutePath()).listFiles();
                for (File file : files) {
                    if (file.isFile()) {
                        samples.add(FilenameUtils.removeExtension(file.getName())) ;
                    }
                }
                Collections.sort(samples, new MyComparator());
                convertJPGtoMovie(selectedDirectory.getAbsolutePath()+"\\",samples,"C:\\Users\\Lana\\Desktop\\VideoEditorTemp\\create.mp4",30);
                System.out.println("video created");
                samples.clear();
            }
        });
        startPn.getChildren().add(create);
        ///////////////////////////////////////////////end of create///////////////////////////////////////////////////////////////////
        //////////////////////////////////////merge videos////////////////////////////////////////////////
        Button merge=new Button("Merge Videos");
        merge.setPrefWidth(200);
        merge.setPrefHeight(50);
        merge.setLayoutX(400);
        merge.setLayoutY(400);
        merge.setOnAction(actionEvent -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(stage);
            if(selectedDirectory != null){
                File[] files = new File(selectedDirectory.getAbsolutePath()).listFiles();
                for (File file : files) {
                    if (file.isFile()) {
                        samples.add(FilenameUtils.removeExtension(file.getName())) ;
                    }
                }
                int n=0;
                for(int i=0;i<samples.size();i++)
                {
                    try {
                        frameInfo=convertMovietoJPG(selectedDirectory.getAbsolutePath()+"\\"+samples.get(i)+".mp4", "C:\\Users\\Lana\\Desktop\\Images","jpg",0,n);
                        n=frameInfo.framesNum;
                    } catch (Exception e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
            samples.clear();
            File[] files = new File("C:/Users/Lana/Desktop/Images").listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    samples.add(FilenameUtils.removeExtension(file.getName())) ;
                }
            }
            Collections.sort(samples, new MyComparator());
            convertJPGtoMovie("C:\\Users\\Lana\\Desktop\\Images\\",samples,"C:\\Users\\Lana\\Desktop\\VideoEditorTemp\\merge.mp4",30);
            System.out.println("videos merged");
            samples.clear();
            final File[] filesD = new File("C:/Users/Lana/Desktop/Images").listFiles();
            for (File file : filesD) {
                if (file.isFile()) {
                  file.delete();
                }
            }
        });
        startPn.getChildren().add(merge);
        /////////////////////////////////////// end of merge //////////////////////////////////////////////////////////////
        stage.setOnCloseRequest(event -> {
             File[] filesD = new File("C:/Users/Lana/Desktop/Images").listFiles();
            for (File file : filesD) {
                if (file.isFile()) {
                    file.delete();
                }
            }
            filesD = new File("C:/Users/Lana/Desktop/imagewm").listFiles();
            for (File file : filesD) {
                if (file.isFile()) {
                    file.delete();
                }
            }
            filesD = new File("C:/Users/Lana/Desktop/textwm").listFiles();
            for (File file : filesD) {
                if (file.isFile()) {
                    file.delete();
                }
            }
            filesD = new File("C:/Users/Lana/Desktop/VideoEditorTemp").listFiles();
            for (File file : filesD) {
                if (file.isFile()) {
                    file.delete();
                }
            }

        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    static class MyComparator implements Comparator<String> {

        public int compare(String o1, String o2){
            return new BigDecimal(o1).compareTo(new BigDecimal(o2));
        }

    }
    private Slider createSlider(Scene scene) {
        Slider slider = new Slider();
        slider.setMin(0);
        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                slider.setMax(mediaPlayer.getMedia().getDuration().toSeconds());
            }
        });
        System.out.println(mediaPlayer.getMedia().getDuration());

        slider.setPrefWidth(1000);
        slider.setValue(0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);

        slider.valueProperty().addListener((ObservableValue<? extends Number> observable,
                                            Number oldValue, Number newValue) -> {
            if (slider.isPressed()) {
                long dur = newValue.intValue() * 1000;
                mediaPlayer.seek(new Duration(dur));
            }
        });
        slider.translateYProperty().bind(scene.heightProperty().subtract(30));
        return slider;
    }
    public static FrameInfo convertMovietoJPG(String mp4Path, String imagePath, String imgType, int frameJump,int n) throws Exception, IOException
    {
        Java2DFrameConverter converter = new Java2DFrameConverter();
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(mp4Path);
        frameGrabber.start();
        Frame frame;
        double frameRate=frameGrabber.getFrameRate();
        int frames=frameGrabber.getLengthInFrames();
        int imgNum=n;
        System.out.println("Video has "+frameGrabber.getLengthInFrames()+" frames and has frame rate of "+frameRate);
        try {
            for(int ii=1;ii<=frameGrabber.getLengthInFrames();ii++){

                frameGrabber.setFrameNumber(ii);
                frame = frameGrabber.grab();
                BufferedImage bi = converter.convert(frame);
                String path = imagePath+File.separator+imgNum+".jpg";
                ImageIO.write(bi,imgType, new File(path));
                ii+=frameJump;
                imgNum++;
            }
            frameGrabber.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new FrameInfo(frames,(int)frameRate);
    }
    public static void convertJPGtoMovie(String folderPath,ArrayList<String> links, String vidPath,double rate)
    {
        Java2DFrameConverter converter=new Java2DFrameConverter();
        OpenCVFrameConverter.ToIplImage grabberConverter = new OpenCVFrameConverter.ToIplImage();
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(vidPath,640,720);
        try {
            recorder.setFrameRate(rate);
            recorder.setVideoBitrate(9000);
            recorder.setFormat("mp4");
            recorder.setVideoQuality(0); // maximum quality
            recorder.start();
            for (int i=0;i<links.size();i++)
            {
               // recorder.record(grabberConverter.convert(cvLoadImage(links.get(i))));
                BufferedImage img=ImageIO.read(new File(folderPath+links.get(i)+".jpg"));
                Frame frame=new Frame();
                frame=converter.convert(img);
                recorder.record(frame);
            }
            recorder.stop();

        }
        catch (FrameRecorder.Exception | IOException e){
            e.printStackTrace();
        }
    }
    static void addTextWatermark(String text, ArrayList<String> images, String destImageFile,float a,double rate) {
        try {
            for(int i=0;i<images.size();i++)
            {
                BufferedImage sourceImage = ImageIO.read(new File("C:\\Users\\Lana\\Desktop\\Images\\"+images.get(i)+".jpg"));
                Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();
                // initializes necessary graphic properties
                AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a);
                g2d.setComposite(alphaChannel);
                g2d.setColor(Color.BLUE);
                g2d.setFont(new Font("Arial", Font.BOLD, 64));
                FontMetrics fontMetrics = g2d.getFontMetrics();
                Rectangle2D rect = fontMetrics.getStringBounds(text, g2d);

                // calculates the coordinate where the String is painted
                int centerX = (sourceImage.getWidth() - (int) rect.getWidth()) / 2;
                int centerY = sourceImage.getHeight() / 2;
                // paints the textual watermark
                g2d.drawString(text, centerX, centerY);

                String path = "C:\\Users\\Lana\\Desktop\\Images\\"+File.separator+images.get(i)+".jpg";
                ImageIO.write(sourceImage, "jpg", new File(path));
                g2d.dispose();
            }
            System.out.println("The text watermark is added to the image.");
            tempSamples.clear();
            File[] files = new File("C:/Users/Lana/Desktop/Images").listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    tempSamples.add(FilenameUtils.removeExtension(file.getName())) ;
                }
            }
            Collections.sort(tempSamples, new MyComparator());
            convertJPGtoMovie("C:\\Users\\Lana\\Desktop\\Images\\",tempSamples,"C:\\Users\\Lana\\Desktop\\VideoEditorTemp\\textWaterMark.mp4",rate);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    static void addImageWatermark(String watermarkImageFile,  ArrayList<String> images, String destImageFile,float a,double rate) {
        try {

            BufferedImage watermarkImage = ImageIO.read(new File(watermarkImageFile));
               for(int i=0;i<images.size();i++)
            {
                BufferedImage sourceImage = ImageIO.read(new File("C:\\Users\\Lana\\Desktop\\Images\\"+images.get(i)+".jpg"));
                // initializes necessary graphic properties
                Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();
                AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a);
                g2d.setComposite(alphaChannel);
                // calculates the coordinate where the image is painted
                int topLeftX = (sourceImage.getWidth() - watermarkImage.getWidth()) / 2;
                int topLeftY = (sourceImage.getHeight() - watermarkImage.getHeight()) / 2;

                // paints the image watermark
                g2d.drawImage(watermarkImage, topLeftX, topLeftY, null);
                String path = "C:\\Users\\Lana\\Desktop\\Images\\"+File.separator+images.get(i)+".jpg";
                ImageIO.write(sourceImage, "jpg", new File(path));
                g2d.dispose();
            }
            System.out.println("The image watermark is added to the image.");
            tempSamples.clear();
            File[] files = new File("C:/Users/Lana/Desktop/Images").listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    tempSamples.add(FilenameUtils.removeExtension(file.getName())) ;
                }
            }
            Collections.sort(tempSamples, new MyComparator());
            convertJPGtoMovie("C:\\Users\\Lana\\Desktop\\Images\\",tempSamples,"C:\\Users\\Lana\\Desktop\\VideoEditorTemp\\imgWaterMark.mp4",rate);

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}