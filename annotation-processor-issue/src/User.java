import org.immutables.value.Value;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Value.Immutable
@Value.Style(stagedBuilder = true)
public interface User {
    UUID getUserId();

    String getEmail();

    LocalDateTime getCreatedTimestamp();

    boolean isProtectionEnabled();

    boolean isApproved();

    Optional<String> getFirstName();

    Optional<String> getLastName();

    Optional<String> getPhoneNumber();
}
