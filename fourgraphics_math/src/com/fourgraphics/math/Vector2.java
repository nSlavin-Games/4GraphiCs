package com.fourgraphics.math;

import com.fourgraphics.scenes.SceneManager;

public class Vector2
{
    public static final float kEpsilon = 0.00001f;
    private static final float kEpsilonNormalSqrt = 1e-15f;

    public static Vector2 right()
    {
        return new Vector2(1,0);
    }
    public static Vector2 left()
    {
        return new Vector2(-1,0);
    }
    public static Vector2 up()
    {
        return new Vector2(0,1);
    }
    public static Vector2 down()
    {
        return new Vector2(0,-1);
    }
    public static Vector2 zero()
    {
        return new Vector2(0,0);
    }
    public static Vector2 one()
    {
        return new Vector2(1,1);
    }
    public static Vector2 negativeInfinity()
    {
        return new Vector2(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
    }
    public static Vector2 positiveInfinity()
    {
        return new Vector2(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
    }

    public float x;
    public float y;

    //region Constructors
    public Vector2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2(float value)
    {
        x = value;
        y = value;
    }

    public Vector2()
    {
        x = 0;
        y = 0;
    }

    public Vector2(Vector2 other)
    {
        x = other.x;
        y = other.y;
    }
    //endregion

    //region Equals
    public boolean Equals(float x, float y)
    {
        return this.x == x && this.y == y;
    }

    public boolean Equals(Vector2 other)
    {
        return x == other.x && y == other.y;
    }

    public boolean Equals(float value)
    {
        return x == value && y == value;
    }
    //endregion

    //region Sum to current vector
    public Vector2 Sum(float x, float y)
    {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2 Sum(Vector2 other)
    {
        x += other.x;
        y += other.y;
        return this;
    }

    public Vector2 Sum(float value)
    {
        x += value;
        y += value;
        return this;
    }
    //endregion

    //region Sum and return new Vector
    public Vector2 SumN(float x, float y)
    {
        return new Vector2(this.x + x, this.y + y);
    }

    public Vector2 SumN(Vector2 other)
    {
        return new Vector2(x + other.x, y + other.y);
    }

    public Vector2 SumN(float value)
    {
        return new Vector2(x + value, y + value);
    }
    //endregion

    //region Multiply current vector
    public Vector2 Multiply(float x, float y)
    {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vector2 Multiply(Vector2 other)
    {
        x *= other.x;
        y *= other.y;
        return this;
    }

    public Vector2 Multiply(float value)
    {
        x *= value;
        y *= value;
        return this;
    }
    //endregion

    //region Multiply and return new Vector
    public Vector2 MultiplyN(float x, float y)
    {
        return new Vector2(this.x * x, this.y * y);
    }

    public Vector2 MultiplyN(Vector2 other)
    {
        return new Vector2(x * other.x, y * other.y);
    }

    public Vector2 MultiplyN(float value)
    {
        return new Vector2(x * value, y * value);
    }
    //endregion

    //region Divide current vector
    public Vector2 Divide(float x, float y)
    {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vector2 Divide(Vector2 other)
    {
        x /= other.x;
        y /= other.y;
        return this;
    }

    public Vector2 Divide(float value)
    {
        x /= value;
        y /= value;
        return this;
    }
    //endregion

    //region Divide and return new Vector
    public Vector2 DivideN(float x, float y)
    {
        return new Vector2(this.x / x, this.y / y);
    }

    public Vector2 DivideN(Vector2 other)
    {
        return new Vector2(x / other.x, y / other.y);
    }

    public Vector2 DivideN(float value)
    {
        return new Vector2(x / value, y / value);
    }
    //endregion

    //region Magnitude
    /**
     * The square rooted length of the Vector
     * @return
     */
    public float Magnitude()
    {
        return (float)Math.sqrt(x*x+y*y);
    }

    /**
     * Length of the Vector without square rooting it
     * @return
     */
    public float SqrMagnitude()
    {
        return x*x+y*y;
    }
    //endregion

    //region Normalization
    /**
     * Returns a new Vector (based on the current one) with the length of 1
     * @return
     */
    public Vector2 Normalized()
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
    public Vector2 Normalize()
    {
        float mag = Magnitude();
        if(mag > kEpsilon)
            return Divide(Magnitude());

        Vector2 zero = zero();
        this.x = zero.x;
        this.y = zero.y;
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
    public static float Angle(Vector2 from, Vector2 to)
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
    public static Vector2 ClampMagnitude(Vector2 vector, float maxLength)
    {
        float sqrMagnitude = vector.SqrMagnitude();
        if(sqrMagnitude > maxLength * maxLength)
        {
            float mag = vector.Magnitude();

            float normalized_x = vector.x / mag;
            float normalized_y = vector.y / mag;
            return new Vector2(normalized_x * maxLength, normalized_y * maxLength);
        }
        return vector;
    }

    /**
     * Returns the distance between two vectors
     * @param first
     * @param second
     * @return
     */
    public static float Distance(Vector2 first, Vector2 second)
    {
        float diffX = first.x - second.x;
        float diffY = first.y - second.y;
        return Mathf.Sqrt(diffX*diffX + diffY*diffY);
    }

    /**
     * Dot Product of two vectors.
     * @param first
     * @param second
     * @return
     */
    public static float Dot(Vector2 first, Vector2 second)
    {
        return first.x*second.x + first.y*second.y;
    }

    /**
     * Linearly interpolates between vectors a and b by t.
     * @param first
     * @param second
     * @param t
     * @return
     */
    public static Vector2 Lerp(Vector2 first, Vector2 second, float t)
    {
        t = Mathf.Clamp01(t);
        return new Vector2(first.x + (second.x - first.x) * t, first.y + (second.y - first.y) * t);
    }

    /**
     * Linearly interpolates between vectors a and b by t.
     * @param first
     * @param second
     * @param t
     * @return
     */
    public static Vector2 LerpUnclamped(Vector2 first, Vector2 second, float t)
    {
        return new Vector2(first.x + (second.x - first.x) * t, first.y + (second.y - first.y) * t);
    }

    /**
     * Returns a vector that is made from the largest components of two vectors.
     * @param first
     * @param second
     * @return
     */
    public static Vector2 Max(Vector2 first, Vector2 second)
    {
        return new Vector2(Mathf.Max(first.x,second.x),Mathf.Max(first.y,second.y));
    }

    /**
     * Returns a vector that is made from the smallest components of two vectors.
     * @param first
     * @param second
     * @return
     */
    public static Vector2 Min(Vector2 first, Vector2 second)
    {
        return new Vector2(Mathf.Min(first.x,second.x),Mathf.Min(first.y,second.y));
    }

    /**
     * Moves a point current towards target.
     * @param current
     * @param target
     * @param maxDistanceDelta
     * @return
     */
    public static Vector2 MoveTowards(Vector2 current, Vector2 target, float maxDistanceDelta)
    {
        float distX = target.x - current.x;
        float distY = target.y - current.y;

        float sqDist = distX*distX + distY*distY;

        if(sqDist == 0 || (maxDistanceDelta >= 0 && sqDist <= maxDistanceDelta * maxDistanceDelta))
            return target;

        float dist = Mathf.Sqrt(sqDist);

        return new Vector2(current.x + distX/dist * maxDistanceDelta, current.y + distY/dist * maxDistanceDelta);
    }

    /**
     * Returns the 2D vector perpendicular to this 2D vector.
     * The result is always rotated 90-degrees in a counter-clockwise direction for a 2D coordinate system where the positive Y axis goes up.
     * @param inDirection
     * @return
     */
    public static Vector2 Perpendicular(Vector2 inDirection)
    {
        return new Vector2(-inDirection.y, inDirection.x);
    }

    /**
     * Reflects a vector off the vector defined by a normal.
     * @param inDirection
     * @param inNormal
     * @return
     */
    public static Vector2 Reflect(Vector2 inDirection, Vector2 inNormal)
    {
        float factor = -2f * Dot(inNormal, inDirection);
        return new Vector2(factor * inNormal.x + inDirection.x, factor * inNormal.y + inDirection.y);
    }

    /**
     * Multiplies two vectors component-wise.
     * @param first
     * @param second
     * @return
     */
    public static Vector2 Scale(Vector2 first, Vector2 second)
    {
        return new Vector2(first.x*second.x, first.y*second.y);
    }

    /**
     * Returns the signed angle in degrees between from and to.
     * @param from
     * @param to
     * @return
     */
    public static float SignedAngle(Vector2 from, Vector2 to)
    {
        float unsignedAngle = Angle(from,to);
        float sign = Mathf.Sign(from.x*to.y - from.y*to.y);
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
    public static Vector2 SmoothDamp(Vector2 current, Vector2 target, Vector2 currentVelocity, float smoothTime, float maxSpeed)
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
    public static Vector2 SmoothDamp(Vector2 current, Vector2 target, Vector2 currentVelocity, float smoothTime)
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
    public static Vector2 SmoothDamp(Vector2 current, Vector2 target, Vector2 currentVelocity, float smoothTime, float maxSpeed, float deltaTime)
    {
        // Based on Game Programming Gems 4 Chapter 1.10
        smoothTime = Mathf.Max(0.0001F, smoothTime);
        float omega = 2F / smoothTime;

        float x = omega * deltaTime;
        float exp = 1F / (1F + x + 0.48F * x * x + 0.235F * x * x * x);

        float change_x = current.x - target.x;
        float change_y = current.y - target.y;
        Vector2 originalTo = target;

        // Clamp maximum speed
        float maxChange = maxSpeed * smoothTime;

        float maxChangeSq = maxChange * maxChange;
        float sqDist = change_x * change_x + change_y * change_y;
        if (sqDist > maxChangeSq)
        {
            var mag = (float)Math.sqrt(sqDist);
            change_x = change_x / mag * maxChange;
            change_y = change_y / mag * maxChange;
        }

        target.x = current.x - change_x;
        target.y = current.y - change_y;

        float temp_x = (currentVelocity.x + omega * change_x) * deltaTime;
        float temp_y = (currentVelocity.y + omega * change_y) * deltaTime;

        currentVelocity.x = (currentVelocity.x - omega * temp_x) * exp;
        currentVelocity.y = (currentVelocity.y - omega * temp_y) * exp;

        float output_x = target.x + (change_x + temp_x) * exp;
        float output_y = target.y + (change_y + temp_y) * exp;

        // Prevent overshooting
        float origMinusCurrent_x = originalTo.x - current.x;
        float origMinusCurrent_y = originalTo.y - current.y;
        float outMinusOrig_x = output_x - originalTo.x;
        float outMinusOrig_y = output_y - originalTo.y;

        if (origMinusCurrent_x * outMinusOrig_x + origMinusCurrent_y * outMinusOrig_y > 0)
        {
            output_x = originalTo.x;
            output_y = originalTo.y;

            currentVelocity.x = (output_x - originalTo.x) / deltaTime;
            currentVelocity.y = (output_y - originalTo.y) / deltaTime;
        }
        return new Vector2(output_x, output_y);
    }
    //endregion

    public String ToString()
    {
        return "[" + x + ";" + y + "]";
    }
}
