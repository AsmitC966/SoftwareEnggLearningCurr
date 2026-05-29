package models;



public class Stream
{
    public String streamId, streamName, deptId;

    public Stream(String input_line)
    {
        String[] p = input_line.split(",");
        streamId = p[0].trim(); streamName = p[1].trim(); deptId = p[2].trim();
    }
}
