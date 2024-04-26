package model;

import java.time.LocalDate;
import java.util.UUID;

public abstract class BaseModel {
    {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDate.now();
    }

    protected UUID id;
    protected LocalDate createdAt;
    protected boolean isActive = true;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
