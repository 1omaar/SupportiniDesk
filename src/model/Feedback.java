/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author GIGABYTE
 */
public class Feedback {
    private int id;
    private String feedback;
    private int id_suivi;
    private int id_user_feedback;
    private int id_coach_feedback;
    private int id_entrainee;

    public Feedback(String feedback, int id_suivi, int id_user_feedback, int id_coach_feedback, int id_entrainee) {
        this.feedback = feedback;
        this.id_suivi = id_suivi;
        this.id_user_feedback = id_user_feedback;
        this.id_coach_feedback = id_coach_feedback;
        this.id_entrainee = id_entrainee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getId_suivi() {
        return id_suivi;
    }

    public void setId_suivi(int id_suivi) {
        this.id_suivi = id_suivi;
    }

    public int getId_user_feedback() {
        return id_user_feedback;
    }

    public void setId_user_feedback(int id_user_feedback) {
        this.id_user_feedback = id_user_feedback;
    }

    public int getId_coach_feedback() {
        return id_coach_feedback;
    }

    public void setId_coach_feedback(int id_coach_feedback) {
        this.id_coach_feedback = id_coach_feedback;
    }

    public int getId_entrainee() {
        return id_entrainee;
    }

    public void setId_entrainee(int id_entrainee) {
        this.id_entrainee = id_entrainee;
    }

    @Override
    public String toString() {
        return "feedback{" + "feedback=" + feedback + '}';
    }
    
    
    
}
