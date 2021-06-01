package com.fourgraphics.math;
import com.fourgraphics.scenes.SceneManager;

public class Vector3
{
    public static final float kEpsilon = 0.00001f;
    private static final float kEpsilonNormalSqrt = 1e-15f;

    public static Vector3 right()
    {
        return new Vector3(1,0,0);
    }
    public static Vector3 left()
    {
        return new Vector3(-1,0,0);
    }
    public static Vector3 up()
    {
        return new Vector3(0,1,0);
    }
    public static Vector3 down()
    {
        return new Vector3(0,-1,0);
    }
    public static Vector3 forward()
    {
        return new Vector3(0,0,1);
    }
    public static Vector3 back()
    {
        return new Vector3(0,0,-1);
    }
    public static Vector3 zero()
    {
        return new Vector3(0,0,0);
    }
    public static Vector3 one()
    {
        return new Vector3(1,1,1);
    }
    public static Vector3 negativeInfinity()
    {
        return new Vector3(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
    }
    public static Vector3 positiveInfinity()
    {
        return new Vector3(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
    }

    public float x;
    public float y;
    public float z;

    //region Constructors
    public Vector3(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(float value)
    {
        x = value;
        y = value;
        z = value;
    }

    public Vector3()
    {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vector3(Vector3 other)
    {
        x = other.x;
        y = other.y;
        z = other.z;
    }
    //endregion

    //region Equals
    public boolean Equals(float x, float y, float z)
    {
        return this.x == x && this.y == y && this.z == z;
    }

    public boolean Equals(Vector3 other)
    {
        return x == other.x && y == other.y && z == other.z;
    }

    public boolean Equals(float value)
    {
        return x == value && y == value && z == value;
    }
    //endregion

    //region Sum to current vector
    public Vector3 Sum(float x, float y, float z)
    {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Vector3 Sum(Vector3 other)
    {
        x += other.x;
        y += other.y;
        z += other.z;
        return this;
    }

    public Vector3 Sum(float value)
    {
        x += value;
        y += value;
        z += value;
        return this;
    }
    //endregion

    //region Sum and return new Vector
    public Vector3 SumN(float x, float y, float z)
    {
        return new Vector3(this.x + x, this.y + y, this.z + z);
    }

    public Vector3 SumN(Vector3 other)
    {
        return new Vector3(x + other.x, y + other.y, z + other.z);
    }

    public Vector3 SumN(float value)
    {
        return new Vector3(x + value, y + value, z + value);
    }
    //endregion

    //region Multiply current vector
    public Vector3 Multiply(float x, float y, float z)
    {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        return this;
    }

    public Vector3 Multiply(Vector3 other)
    {
        x *= other.x;
        y *= other.y;
        z *= other.z;
        return this;
    }

    public Vector3 Multiply(float value)
    {
        x *= value;
        y *= value;
        z *= value;
        return this;
    }
    //endregion

    //region Multiply and return new Vector
    public Vector3 MultiplyN(float x, float y, float z)
    {
        return new Vector3(this.x * x, this.y * y, this.z * z);
    }

    public Vector3 MultiplyN(Vector3 other)
    {
        return new Vector3(x * other.x, y * other.y, z * other.z);
    }

    public Vector3 MultiplyN(float value)
    {
        return new Vector3(x * value, y * value, z * value);
    }
    //endregion

    //region Divide current vector
    public Vector3 Divide(float x, float y, float z)
    {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        return this;
    }

    public Vector3 Divide(Vector3 other)
    {
        x /= other.x;
        y /= other.y;
        z /= other.z;
        return this;
    }

    public Vector3 Divide(float value)
    {
        x /= value;
        y /= value;
        z /= value;
        return this;
    }
    //endregion

    //region Divide and return new Vector
    public Vector3 DivideN(float x, float y, float z)
    {
        return new Vector3(this.x / x, this.y / y, this.z / z);
    }

    public Vector3 DivideN(Vector3 other)
    {
        return new Vector3(x / other.x, y / other.y, z / other.z);
    }

    public Vector3 DivideN(float value)
    {
        return new Vector3(x / value, y / value, z / value);
    }
    //endregion

    //region Magnitude
    /**
     * The square rooted length of the Vector
     * @return
     */
    public float Magnitude()
    {
        return (float)Math.sqrt(x*x+y*y+z*z);
    }

    /**
     * Length of the Vector without square rooting it
     * @return
     */
    public float SqrMagnitude()
    {
        return x*x+y*y+z*z;
    }
    //endregion

    //region Normalization
    /**
     * Returns a new Vector (based on the current one) with the length of 1
     * @return
     */
    public Vector3 Normalized()
    {
        float mag = Magnitude();
        if(mag > kEpsilon)
            return DivideN(Magnitude());
        else
            return zero();
    }

    /**
     * Makes the Vector's length = 1;
     * @return
     */
    public Vector3 Normalize()
    {
        float mag = Magnitude();
        if(mag > kEpsilon)
            return Divide(Magnitude());

        Vector3 zero = zero();
        this.x = zero.x;
        this.y = zero.y;
        this.z = zero.z;
        return this;
    }
    //endregion

    //region Utils

    /**
     * Returns the unsigned angle in degrees between from and to.
     * @param from
     * @param to
     * @return
     */
    public static float Angle(Vector3 from, Vector3 to)
    {
        // sqrt(a) * sqrt(b) = sqrt(a * b) -- valid for real numbers
        float denominator = (float)Math.sqrt(from.SqrMagnitude() * to.SqrMagnitude());
        if (denominator < kEpsilonNormalSqrt)
            return 0f;

        float dot = Mathf.Clamp(Dot(from, to) / denominator, -1F, 1F);
        return (float)Math.acos(dot) * Mathf.Rad2Deg;
    }

    /**
     * Returns a copy of vector with its magnitude clamped to maxLength.
     * @return
     */
    public static Vector3 ClampMagnitude(Vector3 vector, float maxLength)
    {
        float sqrMagnitude = vector.SqrMagnitude();
        if(sqrMagnitude > maxLength * maxLength)
        {
            float mag = vector.Magnitude();

            float normalized_x = vector.x / mag;
            float normalized_y = vector.y / mag;
            float normalized_z = vector.z / mag;
            return new Vector3(normalized_x * maxLength, normalized_y * maxLength, normalized_z * maxLength);
        }
        return vector;
    }

    /**
     * Cross Product of two vectors.
     * @param first
     * @param second
     * @return
     */
    public static Vector3 Cross(Vector3 first, Vector3 second)
    {
        return new Vector3(first.y * second.z - first.z * second.y,
                first.z * second.x - first.x * second.z,
                first.x * second.y - first.y * second.x);
    }

    /**
     * Returns the distance between two vectors
     * @param first
     * @param second
     * @return
     */
    public static float Distance(Vector3 first, Vector3 second)
    {
        float diffX = first.x - second.x;
        float diffY = first.y - second.y;
        float diffZ = first.z - second.z;
        return Mathf.Sqrt(diffX*diffX + diffY*diffY + diffZ*diffZ);
    }

    /**
     * Dot Product of two vectors.
     * @param first
     * @param second
     * @return
     */
    public static float Dot(Vector3 first, Vector3 second)
    {
        return first.x*second.x + first.y*second.y + first.z*second.z;
    }

    /**
     * Linearly interpolates between vectors a and b by t.
     * @param first
     * @param second
     * @param t
     * @return
     */
    public static Vector3 Lerp(Vector3 first, Vector3 second, float t)
    {
        t = Mathf.Clamp01(t);
        return new Vector3(first.x + (second.x - first.x) * t, first.y + (second.y - first.y) * t, first.z + (second.z - first.z) * t);
    }

    /**
     * Linearly interpolates between vectors a and b by t.
     * @param first
     * @param second
     * @param t
     * @return
     */
    public static Vector3 LerpUnclamped(Vector3 first, Vector3 second, float t)
    {
        return new Vector3(first.x + (second.x - first.x) * t, first.y + (second.y - first.y) * t, first.z + (second.z - first.z) * t);
    }

    /**
     * Returns a vector that is made from the largest components of two vectors.
     * @param first
     * @param second
     * @return
     */
    public static Vector3 Max(Vector3 first, Vector3 second)
    {
        return new Vector3(Mathf.Max(first.x,second.x),Mathf.Max(first.y,second.y),Mathf.Max(first.z,second.z));
    }

    /**
     * Returns a vector that is made from the smallest components of two vectors.
     * @param first
     * @param second
     * @return
     */
    public static Vector3 Min(Vector3 first, Vector3 second)
    {
        return new Vector3(Mathf.Min(first.x,second.x),Mathf.Min(first.y,second.y),Mathf.Min(first.z,second.z));
    }

    /**
     * Moves a point current towards target.
     * @param current
     * @param target
     * @param maxDistanceDelta
     * @return
     */
    public static Vector3 MoveTowards(Vector3 current, Vector3 target, float maxDistanceDelta)
    {
        float distX = target.x - current.x;
        float distY = target.y - current.y;
        float distZ = target.z - current.z;

        float sqDist = distX*distX + distY*distY + distZ*distZ;

        if(sqDist == 0 || (maxDistanceDelta >= 0 && sqDist <= maxDistanceDelta * maxDistanceDelta))
            return target;

        float dist = Mathf.Sqrt(sqDist);

        return new Vector3(current.x + distX/dist * maxDistanceDelta, current.y + distY/dist * maxDistanceDelta, current.z + distZ/dist * maxDistanceDelta);
    }

    /**
     * Projects a vector onto another vector.
     * @param vector
     * @param onNormal
     * @return
     */
    public static Vector3 Project(Vector3 vector, Vector3 onNormal)
    {
        float sqrMag = Dot(onNormal,onNormal);
        if(sqrMag < Mathf.Epsilon)
            return zero();
        else
        {
            var dot = Dot(vector, onNormal);
            return new Vector3(onNormal.x*dot/sqrMag, onNormal.y*dot/sqrMag, onNormal.z*dot/sqrMag);
        }
    }

    public static Vector3 ProjectOnPlane(Vector3 vector, Vector3 planeNormal)
    {
        float sqrMag = Dot(planeNormal,planeNormal);
        if(sqrMag < Mathf.Epsilon)
            return vector;
        else
        {
            var dot = Dot(vector, planeNormal);
            return new Vector3(vector.x - planeNormal.x*dot/sqrMag, vector.y - planeNormal.y*dot/sqrMag, vector.z - planeNormal.z*dot/sqrMag);
        }
    }

    /**
     * Reflects a vector off the vector defined by a normal.
     * @param inDirection
     * @param inNormal
     * @return
     */
    public static Vector3 Reflect(Vector3 inDirection, Vector3 inNormal)
    {
        float factor = -2f * Dot(inNormal, inDirection);
        return new Vector3(factor * inNormal.x + inDirection.x, factor * inNormal.y + inDirection.y, factor * inNormal.z + inDirection.z);
    }

    /**
     * Multiplies two vectors component-wise.
     * @param first
     * @param second
     * @return
     */
    public static Vector3 Scale(Vector3 first, Vector3 second)
    {
        return new Vector3(first.x*second.x, first.y*second.y, first.z*second.z);
    }

    /**
     * Returns the signed angle in degrees between from and to.
     * @param from
     * @param to
     * @return
     */
    public static float SignedAngle(Vector3 from, Vector3 to, Vector3 axis)
    {
        float unsignedAngle = Angle(from,to);

        float cross_x = from.y * to.z - from.z * to.y;
        float cross_y = from.z * to.x - from.x * to.z;
        float cross_z = from.x * to.y - from.y * to.x;
        float sign = Mathf.Sign(axis.x * cross_x + axis.y * cross_y + axis.z * cross_z);
        return unsignedAngle * sign;
    }

    /**
     * Gradually changes a vector towards a desired goal over time.
     * @param current
     * @param target
     * @param currentVelocity
     * @param smoothTime
     * @param maxSpeed
     * @return
     */
    public static Vector3 SmoothDamp(Vector3 current, Vector3 target, Vector3 currentVelocity, float smoothTime, float maxSpeed)
    {
        return SmoothDamp(current,target,currentVelocity,smoothTime,maxSpeed, SceneManager.DeltaTime());
    }

    /**
     * Gradually changes a vector towards a desired goal over time.
     * @param current
     * @param target
     * @param currentVelocity
     * @param smoothTime
     * @return
     */
    public static Vector3 SmoothDamp(Vector3 current, Vector3 target, Vector3 currentVelocity, float smoothTime)
    {
        return SmoothDamp(current,target,currentVelocity,smoothTime,Mathf.Infinity,SceneManager.DeltaTime());
    }

    /**
     * Gradually changes a vector towards a desired goal over time.
     * @param current
     * @param target
     * @param currentVelocity
     * @param smoothTime
     * @param maxSpeed
     * @param deltaTime
     * @return
     */
    public static Vector3 SmoothDamp(Vector3 current, Vector3 target, Vector3 currentVelocity, float smoothTime, float maxSpeed, float deltaTime)
    {
        float outputX = 0;
        float outputY = 0;
        float outputZ = 0;

        // Based on Game Programming Gems 4 Chapter 1.10
        smoothTime = Mathf.Max(0.0001F, smoothTime);
        float omega = 2F / smoothTime;

        float x = omega * deltaTime;
        float exp = 1F / (1F + x + 0.48F * x * x + 0.235F * x * x * x);

        float change_x = current.x - target.x;
        float change_y = current.y - target.y;
        float change_z = current.z - target.z;
        Vector3 originalTo = target;

        // Clamp maximum speed
        float maxChange = maxSpeed * smoothTime;

        float maxChangeSq = maxChange * maxChange;
        float sqDist = change_x * change_x + change_y * change_y + change_z * change_z;
        if (sqDist > maxChangeSq)
        {
            var mag = (float)Math.sqrt(sqDist);
            change_x = change_x / mag * maxChange;
            change_y = change_y / mag * maxChange;
            change_z = change_z / mag * maxChange;
        }

        target.x = current.x - change_x;
        target.y = current.y - change_y;
        target.z = current.z - change_z;

        float temp_x = (currentVelocity.x + omega * change_x) * deltaTime;
        float temp_y = (currentVelocity.y + omega * change_y) * deltaTime;
        float temp_z = (currentVelocity.z + omega * change_z) * deltaTime;

        currentVelocity.x = (currentVelocity.x - omega * temp_x) * exp;
        currentVelocity.y = (currentVelocity.y - omega * temp_y) * exp;
        currentVelocity.z = (currentVelocity.z - omega * temp_z) * exp;

        outputX = target.x + (change_x + temp_x) * exp;
        outputY = target.y + (change_y + temp_y) * exp;
        outputZ = target.z + (change_z + temp_z) * exp;

        // Prevent overshooting
        float origMinusCurrent_x = originalTo.x - current.x;
        float origMinusCurrent_y = originalTo.y - current.y;
        float origMinusCurrent_z = originalTo.z - current.z;
        float outMinusOrig_x = outputX - originalTo.x;
        float outMinusOrig_y = outputY - originalTo.y;
        float outMinusOrig_z = outputZ - originalTo.z;

        if (origMinusCurrent_x * outMinusOrig_x + origMinusCurrent_y * outMinusOrig_y + origMinusCurrent_z * outMinusOrig_z > 0)
        {
            outputX = originalTo.x;
            outputY = originalTo.y;
            outputZ = originalTo.z;

            currentVelocity.x = (outputX - originalTo.x) / deltaTime;
            currentVelocity.y = (outputY - originalTo.y) / deltaTime;
            currentVelocity.z = (outputZ - originalTo.z) / deltaTime;
        }
        return new Vector3(outputX, outputY, outputZ);
    }
    //endregion

    public String ToString()
    {
        return "[" + x + ";" + y + ";" + z +"]";
    }
}
