package li.antonio.coding_challenges.interview.validate_ip;

class Main {
    private Main() {
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    static boolean isOctalValue(final String address) {
        try {
            final var value = Integer.parseInt(address);
            if (value < 0 || value > 255) {
                return false;
            }
        } catch (final NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * "172.0.0.1" --> if it's a valid ip address
     * [0-255], 4 parts, separated by '.'
     * <p>
     * "12.3.4.5" --> true
     * "1.23.5.6.7" --> false
     * "abc.1.2.3" --> false
     * ...
     */
    public static boolean isIP(final String address) {
        final var addressParts = address.split("\\.");
        if (addressParts.length != 4) {
            return false;
        }
        for (final String addressPart : addressParts) {
            if (!isOctalValue(addressPart)) {
                return false;
            }
        }
        return true;
    }

    static boolean canBecomeIP(final String address, final int level) {
        for (int i = 0; i < Math.min(3, address.length()); i++) {
            if (!isOctalValue(address.substring(0, i + 1))) {
                return false;
            }
            if (level < 3 && canBecomeIP(address.substring(i + 1), level + 1)) {
                return true;
            } else if (level == 3 && address.length() - i == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * "172001" --> if it can be converted into a valid ip, by adding '.'s
     * "172001" --> true, "172.0.0.1"
     * "87878729" --> true, "87.87.87.29"
     * "889889889" --> false, "88.98.89.889"
     * "283123a8" --> false
     * ...
     */
    public static boolean canBecomeIP(final String address) {
        return canBecomeIP(address, 0);
    }
}

