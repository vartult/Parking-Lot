package com.cellfishpool.utils.strategy;

import com.cellfishpool.exception.NoFreeSlotAvailableException;

import java.util.TreeSet;


public class NaturalOrderingParkingStrategy implements ParkingStrategy {
  TreeSet<Integer> slotTreeSet;

  public NaturalOrderingParkingStrategy() {
    this.slotTreeSet = new TreeSet<>();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addSlot(Integer slotNumber) {
    this.slotTreeSet.add(slotNumber);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeSlot(Integer slotNumber) {
    this.slotTreeSet.remove(slotNumber);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Integer getNextSlot() {
    if (slotTreeSet.isEmpty()) {
      throw new NoFreeSlotAvailableException();
    }
    return this.slotTreeSet.first();
  }
}
