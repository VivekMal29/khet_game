package com.vivek.khet_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import static android.graphics.Paint.Style.FILL;
import static java.lang.Math.abs;
import static java.lang.Math.floor;

public class khet_canvas extends View {

    Bitmap bitmap;
    Canvas myBitmapCanvas;
    Paint linePaint;
    Paint rectPaint;
    Bitmap imageBitmap;

    Bitmap bitmap1;

    Drawable image;
    Boolean pieceSelected=false;
    Boolean touched=false;
    Boolean screenFree=false;
    Boolean start=false;
    int pieceToDraw ;

    int w;
    int h;
    int boxNo = -1;

    int[][] squares =new int[80][2];

    public khet_canvas(Context context) {
        super(context);
        image = getResources().getDrawable(R.drawable.pyramid_red);
        rectPaint = new Paint();
        linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setStrokeWidth(2);
        rectPaint.setColor(Color.WHITE);
        rectPaint.setStyle(FILL);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (bitmap == null) {
            bitmap  = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            myBitmapCanvas = new Canvas(bitmap );
        }




        w = myBitmapCanvas.getWidth();
        h= myBitmapCanvas.getHeight();



        for(int i=-5;i<=5;i++){
            myBitmapCanvas.drawLine((w/2)+(w/12)*i,(h/2)-(w/12)*4,(w/2)+(w/12)*i,(h/2)+(w/12)*4,linePaint);
        }

        for(int i=-4;i<=4;i++){
            myBitmapCanvas.drawLine((w/2)-(w/12)*5,(h/2)+(w/12)*i,(w/2)+(w/12)*5,(h/2)+(w/12)*i,linePaint);
        }


//        RotateBitmap(imageBitmap,90);
        if(touched==true){
            pieceSelected=true;
        }
        if(start==false){
            initialSetup();
            start=true;
        }



        canvas.drawBitmap(bitmap,0,0,null);

    }

    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public void initialSetup(){

        //setting red pyramids
        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pyramid_red);
        bitmap1= Bitmap.createScaledBitmap(imageBitmap,w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2 + 2*w/12, h/2-(4*w/12), null);
        squares[7][0] = 1;
        squares[7][1] = 1;

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pyramid_red);
        bitmap1= Bitmap.createScaledBitmap(RotateBitmap(imageBitmap,270),w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2 -(5*w/12), h/2-w/12, null);
        squares[30][0] = 1;
        squares[30][1] = 4;

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pyramid_red);
        bitmap1= Bitmap.createScaledBitmap(imageBitmap,w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2 -(5*w/12), h/2, null);
        squares[40][0] = 1;
        squares[40][1] = 1;

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pyramid_red);
        bitmap1= Bitmap.createScaledBitmap(RotateBitmap(imageBitmap,180),w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2 - 2* w/12, h/2-(2*w/12), null);
        squares[23][0] = 1;
        squares[23][1] = 3;

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pyramid_red);
        bitmap1= Bitmap.createScaledBitmap(imageBitmap,w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2 +2* w/12, h/2-(w/12), null);
        squares[37][0] = 1;
        squares[37][1] = 1;

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pyramid_red);
        bitmap1= Bitmap.createScaledBitmap(RotateBitmap(imageBitmap,270),w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2 +2* w/12, h/2, null);
        squares[47][0] = 1;
        squares[47][1] = 4;

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pyramid_red);
        bitmap1= Bitmap.createScaledBitmap(RotateBitmap(imageBitmap,270),w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2 +2* w/12, h/2+(2*w/12), null);
        squares[67][0] = 1;
        squares[67][1] = 4;


        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pyramid_silver);
        bitmap1= Bitmap.createScaledBitmap(imageBitmap,w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2 - 3*w/12, h/2+(3*w/12), null);
        squares[72][0] = 5;
        squares[72][1] = 1;

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pyramid_silver);
        bitmap1= Bitmap.createScaledBitmap(imageBitmap,w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2 - 3*w/12, h/2, null);
        squares[42][0] = 5;
        squares[42][1] = 1;

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pyramid_silver);
        bitmap1= Bitmap.createScaledBitmap(RotateBitmap(imageBitmap,270),w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2 - 3*w/12, h/2-(w/12), null);
        squares[32][0] = 5;
        squares[32][1] = 4;

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pyramid_silver);
        bitmap1= Bitmap.createScaledBitmap(RotateBitmap(imageBitmap,270),w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2 - 3*w/12, h/2-(3*w/12), null);
        squares[12][0] = 5;
        squares[12][1] = 4;

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pyramid_silver);
        bitmap1= Bitmap.createScaledBitmap(imageBitmap,w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2 + 4*w/12, h/2, null);
        squares[49][0] = 5;
        squares[49][1] = 1;

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pyramid_silver);
        bitmap1= Bitmap.createScaledBitmap(RotateBitmap(imageBitmap,270),w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2 + 4*w/12, h/2-(w/12), null);
        squares[39][0] = 5;
        squares[39][1] = 4;

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pyramid_silver);
        bitmap1= Bitmap.createScaledBitmap(RotateBitmap(imageBitmap,180),w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2 + w/12, h/2+(w/12), null);
        squares[56][0] = 5;
        squares[56][1] = 3;

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scarab_red);
        bitmap1= Bitmap.createScaledBitmap(imageBitmap,w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2 , h/2-(w/12), null);
        squares[35][0] = 2;
        squares[35][1] = 1;

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scarab_red);
        bitmap1= Bitmap.createScaledBitmap(RotateBitmap(imageBitmap,180),w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2 -(w/12) , h/2, null);
        squares[44][0] = 2;
        squares[44][1] = 3;

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scarab_silver);
        bitmap1= Bitmap.createScaledBitmap(RotateBitmap(imageBitmap,90),w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2 -(w/12) , h/2- (w/12), null);
        squares[34][0] = 6;
        squares[34][1] = 2;

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scarab_silver);
        bitmap1= Bitmap.createScaledBitmap(RotateBitmap(imageBitmap,270),w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2  , h/2, null);
        squares[45][0] = 6;
        squares[45][1] = 4;

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aunbis_red);
        bitmap1= Bitmap.createScaledBitmap(imageBitmap,w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2 - w/12 , h/2-4*w/12, null);
        squares[4][0] = 3;
        squares[4][1] = 1;

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aunbis_red);
        bitmap1= Bitmap.createScaledBitmap(imageBitmap,w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2 +w/12 , h/2-4*w/12, null);
        squares[6][0] = 3;
        squares[6][1] = 1;

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.anubis_silver);
        bitmap1= Bitmap.createScaledBitmap(imageBitmap,w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2 -(2*w/12) , h/2+3*w/12, null);
        squares[73][0] = 7;
        squares[73][1] = 1;

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.anubis_silver);
        bitmap1= Bitmap.createScaledBitmap(imageBitmap,w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2  , h/2+3*w/12, null);
        squares[75][0] = 7;
        squares[75][1] = 1;

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pharoah_red);
        bitmap1= Bitmap.createScaledBitmap(imageBitmap,w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2  , h/2-4*w/12, null);
        squares[5][0] = 4;
        squares[75][1] = 1;

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pharoah_silver);
        bitmap1= Bitmap.createScaledBitmap(imageBitmap,w/12,w/12,true);
        myBitmapCanvas.drawBitmap(bitmap1,w/2-w/12  , h/2+3*w/12, null);
        squares[74][0] = 8;
        squares[74][1] = 1;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int currentX = (int) event.getX();
        int currentY = (int) event.getY();


        if((currentX>w/2-(5*w/12)&&currentX<w/2+(5*w/12)&&currentY>h/2-(4*w/12)&&currentY<h/2+(4*w/12))&&!pieceSelected&&!touched){


            int disX = currentX - w/12;
            int disY = currentY - (h/2-4*w/12);

            boxNo = (disY/(w/12)) *10 + disX/(w/12);
            if(squares[boxNo][0]!=0){
                touched=true;
            }






        }

        if((currentX>w/2-(5*w/12)&&currentX<w/2+(5*w/12)&&currentY>h/2-(4*w/12)&&currentY<h/2+(4*w/12))&&pieceSelected){
                int lineX = w/2 + (boxNo%10 -5 )*w/12;
                int lineY = h/2 + (boxNo/10 -4 )*w/12;
            Toast.makeText(getContext(), "touched again " + boxNo, Toast.LENGTH_SHORT).show();

                //for free box there are 8 possibilities
                if(abs(lineX-currentX)<w/12&&currentX<lineX&&currentY<lineY&&abs(lineY-currentY)<w/12){
                    if(squares[boxNo-11][0]==0){


                        if(squares[boxNo][0]==1){
                            pieceToDraw = R.drawable.pyramid_red;
                            squares[boxNo-11][0]=1;
                        }
                        else if(squares[boxNo][0]==2){
                            pieceToDraw = R.drawable.scarab_red;
                            squares[boxNo-11][0]=2;
                        }
                        else if(squares[boxNo][0]==3){
                            pieceToDraw = R.drawable.aunbis_red;
                            squares[boxNo-11][0]=3;
                        }
                        else if(squares[boxNo][0]==4){
                            pieceToDraw = R.drawable.pharoah_red;
                            squares[boxNo-11][0]=4;
                        }
                        else if(squares[boxNo][0]==5){
                            pieceToDraw = R.drawable.pyramid_silver;
                            squares[boxNo-11][0]=5;
                        }
                        else if(squares[boxNo][0]==6){
                            pieceToDraw = R.drawable.scarab_silver;
                            squares[boxNo-11][0]=6;
                        }
                        else if(squares[boxNo][0]==7){
                            pieceToDraw = R.drawable.anubis_silver;
                            squares[boxNo-11][0]=7;
                        }
                        else if(squares[boxNo][0]==8){
                            pieceToDraw = R.drawable.pharoah_silver;
                            squares[boxNo-11][0]=8;
                        }
                        squares[boxNo][0] = 0;

                        imageBitmap = BitmapFactory.decodeResource(getResources(),pieceToDraw);
                        bitmap1= Bitmap.createScaledBitmap(imageBitmap,w/12,w/12,true);
                        myBitmapCanvas.drawBitmap(bitmap1,lineX-w/12 , lineY-w/12, null);

                        myBitmapCanvas.drawRect(lineX,lineY,lineX+w/12,lineY+w/12,rectPaint);
                        invalidate();
                    }

                }
                else if(abs(lineX-currentX)<w/12&&currentX>lineX&&currentY<lineY&&abs(lineY-currentY)<w/12) {
                    if (squares[boxNo - 10][0] == 0) {


                        if (squares[boxNo][0] == 1) {
                            pieceToDraw = R.drawable.pyramid_red;
                            squares[boxNo - 10][0] = 1;
                        } else if (squares[boxNo][0] == 2) {
                            pieceToDraw = R.drawable.scarab_red;
                            squares[boxNo - 10][0] = 2;
                        } else if (squares[boxNo][0] == 3) {
                            pieceToDraw = R.drawable.aunbis_red;
                            squares[boxNo - 10][0] = 3;
                        } else if (squares[boxNo][0] == 4) {
                            pieceToDraw = R.drawable.pharoah_red;
                            squares[boxNo - 10][0] = 4;
                        } else if (squares[boxNo][0] == 5) {
                            pieceToDraw = R.drawable.pyramid_silver;
                            squares[boxNo - 10][0] = 5;
                        } else if (squares[boxNo][0] == 6) {
                            pieceToDraw = R.drawable.scarab_silver;
                            squares[boxNo - 10][0] = 6;
                        } else if (squares[boxNo][0] == 7) {
                            pieceToDraw = R.drawable.anubis_silver;
                            squares[boxNo - 10][0] = 7;
                        } else if (squares[boxNo][0] == 8) {
                            pieceToDraw = R.drawable.pharoah_silver;
                            squares[boxNo - 10][0] = 8;
                        }
                        squares[boxNo][0] = 0;

                        imageBitmap = BitmapFactory.decodeResource(getResources(), pieceToDraw);
                        bitmap1 = Bitmap.createScaledBitmap(imageBitmap, w / 12, w / 12, true);
                        myBitmapCanvas.drawBitmap(bitmap1, lineX, lineY - w / 12, null);

                        myBitmapCanvas.drawRect(lineX, lineY, lineX + w / 12, lineY + w / 12, rectPaint);
                        invalidate();

                    }
                }
                else if(abs(lineX-currentX)<2*w/12&&currentX>lineX+w/12&&currentY<lineY&&abs(lineY-currentY)<w/12){
                    if (squares[boxNo - 9][0] == 0) {


                        if (squares[boxNo][0] == 1) {
                            pieceToDraw = R.drawable.pyramid_red;
                            squares[boxNo - 9][0] = 1;
                        } else if (squares[boxNo][0] == 2) {
                            pieceToDraw = R.drawable.scarab_red;
                            squares[boxNo - 9][0] = 2;
                        } else if (squares[boxNo][0] == 3) {
                            pieceToDraw = R.drawable.aunbis_red;
                            squares[boxNo - 9][0] = 3;
                        } else if (squares[boxNo][0] == 4) {
                            pieceToDraw = R.drawable.pharoah_red;
                            squares[boxNo - 9][0] = 4;
                        } else if (squares[boxNo][0] == 5) {
                            pieceToDraw = R.drawable.pyramid_silver;
                            squares[boxNo - 9][0] = 5;
                        } else if (squares[boxNo][0] == 6) {
                            pieceToDraw = R.drawable.scarab_silver;
                            squares[boxNo - 9][0] = 6;
                        } else if (squares[boxNo][0] == 7) {
                            pieceToDraw = R.drawable.anubis_silver;
                            squares[boxNo - 9][0] = 7;
                        } else if (squares[boxNo][0] == 8) {
                            pieceToDraw = R.drawable.pharoah_silver;
                            squares[boxNo -9][0] = 8;
                        }
                        squares[boxNo][0] = 0;
                        imageBitmap = BitmapFactory.decodeResource(getResources(), pieceToDraw);
                        bitmap1 = Bitmap.createScaledBitmap(imageBitmap, w / 12, w / 12, true);
                        myBitmapCanvas.drawBitmap(bitmap1, lineX + w/12, lineY - w / 12, null);

                        myBitmapCanvas.drawRect(lineX, lineY, lineX + w / 12, lineY + w / 12, rectPaint);
                        invalidate();

                    }


                }
                else if(abs(lineX-currentX)<w/12&&currentX<lineX&&currentY>lineY&&abs(lineY-currentY)<w/12){

                    if (squares[boxNo - 1][0] == 0) {


                        if (squares[boxNo][0] == 1) {
                            pieceToDraw = R.drawable.pyramid_red;
                            squares[boxNo - 1][0] = 1;
                        } else if (squares[boxNo][0] == 2) {
                            pieceToDraw = R.drawable.scarab_red;
                            squares[boxNo - 1][0] = 2;
                        } else if (squares[boxNo][0] == 3) {
                            pieceToDraw = R.drawable.aunbis_red;
                            squares[boxNo - 1][0] = 3;
                        } else if (squares[boxNo][0] == 4) {
                            pieceToDraw = R.drawable.pharoah_red;
                            squares[boxNo - 1][0] = 4;
                        } else if (squares[boxNo][0] == 5) {
                            pieceToDraw = R.drawable.pyramid_silver;
                            squares[boxNo - 1][0] = 5;
                        } else if (squares[boxNo][0] == 6) {
                            pieceToDraw = R.drawable.scarab_silver;
                            squares[boxNo - 1][0] = 6;
                        } else if (squares[boxNo][0] == 7) {
                            pieceToDraw = R.drawable.anubis_silver;
                            squares[boxNo - 1][0] = 7;
                        } else if (squares[boxNo][0] == 8) {
                            pieceToDraw = R.drawable.pharoah_silver;
                            squares[boxNo - 1][0] = 8;
                        }
                        squares[boxNo][0] = 0;
                        imageBitmap = BitmapFactory.decodeResource(getResources(), pieceToDraw);
                        bitmap1 = Bitmap.createScaledBitmap(imageBitmap, w / 12, w / 12, true);
                        myBitmapCanvas.drawBitmap(bitmap1, lineX-w/12, lineY , null);

                        myBitmapCanvas.drawRect(lineX, lineY, lineX + w / 12, lineY + w / 12, rectPaint);
                        invalidate();

                    }

                }
                else if(abs(lineX-currentX)<2*w/12&&currentX>lineX+w/12&&currentY>lineY&&abs(lineY-currentY)<w/12){

                    if (squares[boxNo +1][0] == 0) {


                        if (squares[boxNo][0] == 1) {
                            pieceToDraw = R.drawable.pyramid_red;
                            squares[boxNo + 1][0] = 1;
                        } else if (squares[boxNo][0] == 2) {
                            pieceToDraw = R.drawable.scarab_red;
                            squares[boxNo + 1][0] = 2;
                        } else if (squares[boxNo][0] == 3) {
                            pieceToDraw = R.drawable.aunbis_red;
                            squares[boxNo + 1][0] = 3;
                        } else if (squares[boxNo][0] == 4) {
                            pieceToDraw = R.drawable.pharoah_red;
                            squares[boxNo + 1][0] = 4;
                        } else if (squares[boxNo][0] == 5) {
                            pieceToDraw = R.drawable.pyramid_silver;
                            squares[boxNo + 1][0] = 5;
                        } else if (squares[boxNo][0] == 6) {
                            pieceToDraw = R.drawable.scarab_silver;
                            squares[boxNo + 1][0] = 6;
                        } else if (squares[boxNo][0] == 7) {
                            pieceToDraw = R.drawable.anubis_silver;
                            squares[boxNo + 1][0] = 7;
                        } else if (squares[boxNo][0] == 8) {
                            pieceToDraw = R.drawable.pharoah_silver;
                            squares[boxNo + 1][0] = 8;
                        }
                        squares[boxNo][0] = 0;
                        imageBitmap = BitmapFactory.decodeResource(getResources(), pieceToDraw);
                        bitmap1 = Bitmap.createScaledBitmap(imageBitmap, w / 12, w / 12, true);
                        myBitmapCanvas.drawBitmap(bitmap1, lineX+w/12, lineY , null);

                        myBitmapCanvas.drawRect(lineX, lineY, lineX + w / 12, lineY + w / 12, rectPaint);
                        invalidate();

                    }

                }
                else if(abs(lineX-currentX)<w/12&&currentX<lineX&&currentY>lineY+w/12&&abs(lineY-currentY)<2*w/12){
                    if (squares[boxNo + 9][0] == 0) {


                        if (squares[boxNo][0] == 1) {
                            pieceToDraw = R.drawable.pyramid_red;
                            squares[boxNo + 9][0] = 1;
                        } else if (squares[boxNo][0] == 2) {
                            pieceToDraw = R.drawable.scarab_red;
                            squares[boxNo + 9][0] = 2;
                        } else if (squares[boxNo][0] == 3) {
                            pieceToDraw = R.drawable.aunbis_red;
                            squares[boxNo + 9][0] = 3;
                        } else if (squares[boxNo][0] == 4) {
                            pieceToDraw = R.drawable.pharoah_red;
                            squares[boxNo + 9][0] = 4;
                        } else if (squares[boxNo][0] == 5) {
                            pieceToDraw = R.drawable.pyramid_silver;
                            squares[boxNo + 9][0] = 5;
                        } else if (squares[boxNo][0] == 6) {
                            pieceToDraw = R.drawable.scarab_silver;
                            squares[boxNo + 9][0] = 6;
                        } else if (squares[boxNo][0] == 7) {
                            pieceToDraw = R.drawable.anubis_silver;
                            squares[boxNo + 9][0] = 7;
                        } else if (squares[boxNo][0] == 8) {
                            pieceToDraw = R.drawable.pharoah_silver;
                            squares[boxNo +9][0] = 8;
                        }

                        squares[boxNo][0] = 0;

                        imageBitmap = BitmapFactory.decodeResource(getResources(), pieceToDraw);
                        bitmap1 = Bitmap.createScaledBitmap(imageBitmap, w / 12, w / 12, true);
                        myBitmapCanvas.drawBitmap(bitmap1, lineX - w/12, lineY + w / 12, null);

                        myBitmapCanvas.drawRect(lineX, lineY, lineX + w / 12, lineY + w / 12, rectPaint);
                        invalidate();

                    }

                }
                else if(abs(lineX-currentX)<w/12&&currentX>lineX&&currentY>lineY+w/12&&abs(lineY-currentY)<2*w/12){
                    if (squares[boxNo + 10][0] == 0) {


                        if (squares[boxNo][0] == 1) {
                            pieceToDraw = R.drawable.pyramid_red;
                            squares[boxNo + 10][0] = 1;
                        } else if (squares[boxNo][0] == 2) {
                            pieceToDraw = R.drawable.scarab_red;
                            squares[boxNo + 10][0] = 2;
                        } else if (squares[boxNo][0] == 3) {
                            pieceToDraw = R.drawable.aunbis_red;
                            squares[boxNo + 10][0] = 3;
                        } else if (squares[boxNo][0] == 4) {
                            pieceToDraw = R.drawable.pharoah_red;
                            squares[boxNo + 10][0] = 4;
                        } else if (squares[boxNo][0] == 5) {
                            pieceToDraw = R.drawable.pyramid_silver;
                            squares[boxNo + 10][0] = 5;
                        } else if (squares[boxNo][0] == 6) {
                            pieceToDraw = R.drawable.scarab_silver;
                            squares[boxNo + 10][0] = 6;
                        } else if (squares[boxNo][0] == 7) {
                            pieceToDraw = R.drawable.anubis_silver;
                            squares[boxNo + 10][0] = 7;
                        } else if (squares[boxNo][0] == 8) {
                            pieceToDraw = R.drawable.pharoah_silver;
                            squares[boxNo + 10][0] = 8;
                        }
                        squares[boxNo][0] = 0;
                        imageBitmap = BitmapFactory.decodeResource(getResources(), pieceToDraw);
                        bitmap1 = Bitmap.createScaledBitmap(imageBitmap, w / 12, w / 12, true);
                        myBitmapCanvas.drawBitmap(bitmap1, lineX, lineY+ w / 12, null);

                        myBitmapCanvas.drawRect(lineX, lineY, lineX + w / 12, lineY + w / 12, rectPaint);
                        invalidate();

                    }

                }
                else if(abs(lineX-currentX)<2*w/12&&currentX>lineX+w/12&&currentY>lineY+w/12&&abs(lineY-currentY)<2*w/12){
                    if(squares[boxNo+11][0]==0){


                        if(squares[boxNo][0]==1){
                            pieceToDraw = R.drawable.pyramid_red;
                            squares[boxNo+11][0]=1;
                        }
                        else if(squares[boxNo][0]==2){
                            pieceToDraw = R.drawable.scarab_red;
                            squares[boxNo+11][0]=2;
                        }
                        else if(squares[boxNo][0]==3){
                            pieceToDraw = R.drawable.aunbis_red;
                            squares[boxNo+11][0]=3;
                        }
                        else if(squares[boxNo][0]==4){
                            pieceToDraw = R.drawable.pharoah_red;
                            squares[boxNo+11][0]=4;
                        }
                        else if(squares[boxNo][0]==5){
                            pieceToDraw = R.drawable.pyramid_silver;
                            squares[boxNo+11][0]=5;
                        }
                        else if(squares[boxNo][0]==6){
                            pieceToDraw = R.drawable.scarab_silver;
                            squares[boxNo+11][0]=6;
                        }
                        else if(squares[boxNo][0]==7){
                            pieceToDraw = R.drawable.anubis_silver;
                            squares[boxNo+11][0]=7;
                        }
                        else if(squares[boxNo][0]==8){
                            pieceToDraw = R.drawable.pharoah_silver;
                            squares[boxNo+11][0]=8;
                        }
                        squares[boxNo][0] = 0;
                        imageBitmap = BitmapFactory.decodeResource(getResources(),pieceToDraw);
                        bitmap1= Bitmap.createScaledBitmap(imageBitmap,w/12,w/12,true);
                        myBitmapCanvas.drawBitmap(bitmap1,lineX+w/12 , lineY+w/12, null);

                        myBitmapCanvas.drawRect(lineX,lineY,lineX+w/12,lineY+w/12,rectPaint);
                        invalidate();
                    }
                }

                touched=false;
                pieceSelected =false;

                return false;
        }
        invalidate();

        return false;
    }
}
