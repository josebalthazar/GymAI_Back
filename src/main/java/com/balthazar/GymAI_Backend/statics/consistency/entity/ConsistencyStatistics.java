package com.balthazar.GymAI_Backend.statics.consistency.entity;

import com.balthazar.GymAI_Backend.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Table(name = "consistency_statistics")
@Getter
public class ConsistencyStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private Integer currentStreak;

    private Integer longestStreak;

    private Integer expectedWorkouts;

    private Integer completedWorkouts;

    private LocalDate lastWorkoutDate;

    protected ConsistencyStatistics() {
    }

    private ConsistencyStatistics(
            Integer currentStreak,
            Integer longestStreak,
            Integer expectedWorkouts,
            Integer completedWorkouts
    ) {
        this.currentStreak = currentStreak;
        this.longestStreak = longestStreak;
        this.expectedWorkouts = expectedWorkouts;
        this.completedWorkouts = completedWorkouts;
    }

    public static ConsistencyStatistics create(User user) {
        return new ConsistencyStatistics(0, 0, 0, 0);
    }

    public double getCompletionRate() {
        if (expectedWorkouts == 0) {
            return 0.0;
        }

        return (double) completedWorkouts / expectedWorkouts * 100;
    }

    public void registerExpectedWorkout() {
        this.expectedWorkouts++;
    }

    public void registerCompletedWorkout() {
        this.completedWorkouts++;
        this.currentStreak++;

        if (this.currentStreak > this.longestStreak) {
            this.longestStreak = this.currentStreak;
        }

        this.lastWorkoutDate = LocalDate.now();
    }

    public void resetStreak() {
        this.currentStreak = 0;
    }
}