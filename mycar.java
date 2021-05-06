package org.yourorghere;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.j2d.TextRenderer;
import java.awt.Font;
//import static com.sun.org.apache.xalan.internal.lib.ExsltMath.cos;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.lang.Math.cos;
import static java.lang.Math.sin;


import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;



/**
 *  <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Lab4 implements GLEventListener {
    //variable for moving object
     float x=-2.9f;
     float x1=0.3f;
     float x3=3.6f;
     float x4=-1.0f;
     float x5=0.2f;
    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new Lab4());
        frame.add(canvas);
        frame.setSize(640, 480);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);
      
        // Setup the drawing area and shading mode
        gl.glClearColor(255/255f, 166/255f, 166/255f, 166/255f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
    }
    


    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!
        
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
    
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        //two polygons for car body
        gl.glTranslatef(x, -2.0f, -6.0f);
        //incrementing variables
        x-=0.01;
        x1-=0.01;
        x3-=0.005;
        x4-=0.001;
        x5-=0.001;
        gl.glColor3f(154 / 255f, 154/ 255f, 154 / 255f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2d(0.8,1);
        gl.glVertex2d(0.8,1.5);
        gl.glVertex2d(4,1.5);
        gl.glVertex2d(4,1);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2d(1.2,1.5);
        gl.glVertex2d(2.3,2.2);
        gl.glVertex2d(3.5,2.2);
        gl.glVertex2d(4,1.5);
        gl.glEnd();
        //polygon for wheel
        gl.glColor3f(69/255f,69/255f,69/255f);
        gl.glTranslatef(x1, -0.1f, -7.0f);
        float numPoints=20;
        float Radius= 0.5f;
        gl.glBegin(GL.GL_POLYGON);
        for( int i=0; i<numPoints; i++ ) { 
          double Angle = i* (2.0*Math.PI/numPoints);
          double X = Math.cos( Angle )*Radius; 
          double Y = Math.sin( Angle )*Radius; 
          gl.glVertex2d( X, Y ); 
        } 
        gl.glEnd();
        ////polygon for wheel
        gl.glTranslatef(x3, -0.2f, -1.3f);
        float numPoints2=20;
        float Radius2= 0.6f;
        gl.glBegin(GL.GL_POLYGON);
        for( int i=0; i<numPoints; i++ ) { 
           double Angle = i* (2.0*Math.PI/numPoints);
           double X = Math.cos( Angle )*Radius; 
           double Y = Math.sin( Angle )*Radius; 
           gl.glVertex2d( X, Y );
        } 
        gl.glEnd();
        //line
        gl.glColor3f(69/255f,69/255f,69/255f);
        gl.glTranslatef(x4, 1.4f, -1.0f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(-0.5f, -0.4f);
        gl.glVertex2f(-0.5f, 1.4f);
        gl.glEnd();
        //line
        gl.glTranslatef(x5, -2.0f, -1.0f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2d(-4.8,1.5);
        gl.glVertex2d(2.8,1.5);
        gl.glEnd();
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

