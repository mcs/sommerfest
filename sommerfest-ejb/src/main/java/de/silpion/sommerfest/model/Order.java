package de.silpion.sommerfest.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ordering")
@NamedQueries({
        @NamedQuery(name = "Order.findAll", query = "SELECT o FROM Order o"),
        @NamedQuery(name = "Order.findByTarget", query = "SELECT o FROM Order o WHERE o.target = :target"),
        @NamedQuery(name = "Order.findByStatus", query = "SELECT o FROM Order o WHERE o.state = :status")
})
public class Order implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private int amount;
    private String target;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn = new Date();
    @Enumerated(EnumType.STRING)
    private ProcessState state = ProcessState.ORDERED;
    @ManyToOne
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public ProcessState getState() {
        return state;
    }

    public void setState(ProcessState state) {
        this.state = state;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", amount=" + amount +
                ", target='" + target + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}