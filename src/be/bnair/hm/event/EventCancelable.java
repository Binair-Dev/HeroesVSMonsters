package be.bnair.hm.event;

public class EventCancelable extends Event
{
    private boolean cancelled = false;

    public boolean isCancelled()
    {
        return cancelled;
    }

    public void isCancelled(boolean cancelled)
    {
        this.cancelled = cancelled;
    }
}