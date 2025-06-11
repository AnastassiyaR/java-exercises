package ee.taltech.iti0202.exam.television;

public class RemoteControl {
    private final Television television;
    private int battery = 100;

    /**
     * Constructs a RemoteControl for the given television with initial battery level 100.
     *
     * @param tv the television to control
     */
    public RemoteControl(Television tv) {
        this.television = tv;
    }

    /**
     * Toggles the television power on or off.
     * Reduces battery level by 10. Does nothing if battery is empty.
     */
    public void pressPowerButton() {
        if (battery <= 0) return;
        if (television.isOn()) {
            television.turnOff();
        } else {
            television.turnOn();
        }
        battery -= 10;
    }

    /**
     * Changes the television channel to the specified number.
     * Reduces battery level by 10. Does nothing if battery is empty.
     *
     * @param channel the new channel to switch to
     */
    public void changeChannel(int channel) {
        if (battery <= 0) return;
        television.goToChannel(channel);
        battery -= 10;
    }

    /**
     * Switches to the previous channel.
     * Reduces battery level by 10. Does nothing if battery is empty.
     */
    public void backChannel() {
        if (battery <= 0) return;
        television.backChannel();
        battery -= 10;
    }

    /**
     * Switches to the next channel.
     * Reduces battery level by 10. Does nothing if battery is empty.
     */
    public void forwardChannel() {
        if (battery <= 0) return;
        television.forwardChannel();
        battery -= 10;
    }

    /**
     * Adds the current channel to the television's favorite channels.
     * Reduces battery level by 10. Does nothing if battery is empty.
     */
    public void addCurrentChannelToFavorites() {
        if (battery <= 0) return;
        television.addFavoriteChannel(television.getChannel());
        battery -= 10;
    }

    /**
     * Removes the current channel from the television's favorite channels.
     * Reduces battery level by 10. Does nothing if battery is empty.
     */
    public void removeCurrentChannelFromFavorites() {
        if (battery <= 0) return;
        television.removeFavoriteChannel(television.getChannel());
        battery -= 10;
    }

    /**
     * Switches to the next favorite channel.
     * Reduces battery level by 10. Does nothing if battery is empty.
     */
    public void nextFavorite() {
        if (battery <= 0) return;
        television.nextFavoriteChannel();
        battery -= 10;
    }

    /**
     * Switches to the previous favorite channel.
     * Reduces battery level by 10. Does nothing if battery is empty.
     */
    public void previousFavorite() {
        if (battery <= 0) return;
        television.previousFavoriteChannel();
        battery -= 10;
    }

    /**
     * Increases the television volume.
     * Reduces battery level by 10. Does nothing if battery is empty.
     */
    public void volumeUp() {
        if (battery <= 0) return;
        television.increaseVolume();
        battery -= 10;
    }

    /**
     * Decreases the television volume.
     * Reduces battery level by 10. Does nothing if battery is empty.
     */
    public void volumeDown() {
        if (battery <= 0) return;
        television.decreaseVolume();
        battery -= 10;
    }

    /**
     * Toggles mute on or off.
     * Reduces battery level by 10. Does nothing if battery is empty.
     */
    public void mute() {
        if (battery <= 0) return;
        television.mute();
        battery -= 10;
    }

    /**
     * Returns the current battery level.
     *
     * @return battery level (0–100)
     */
    public int getBatteryLevel() {
        return battery;
    }

    /**
     * Recharges the remote control battery to 100%.
     */
    public void rechargeBattery() {
        battery = 100;
    }
}
