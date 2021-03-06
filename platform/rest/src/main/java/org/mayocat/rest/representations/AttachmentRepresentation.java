package org.mayocat.rest.representations;

import org.mayocat.model.Attachment;

/**
 * @version $Id$
 */
public class AttachmentRepresentation extends EntityReferenceRepresentation
{
    private FileRepresentation file;

    private String description;

    public AttachmentRepresentation()
    {
        // No-arg constructor required for Jackson deserialization
        super();
    }

    public AttachmentRepresentation(Attachment attachment)
    {
        super(buildAttachmentApiHref(attachment), attachment.getSlug(), attachment.getTitle());
        this.file = buildFileRepresentation(attachment);
        this.description = attachment.getDescription();
    }

    /**
     * Constructor that allows to override the attachment URI and its file representation.
     *
     * Particularly useful for extending classes, such as {@link ImageRepresentation}.
     *
     * @param attachment the attachment to represent.
     * @param uri the URI of the resource represented by the attachment representation
     * @param file the file representation of the attachment representation
     */
    public AttachmentRepresentation(Attachment attachment, String uri, FileRepresentation file)
    {
        super(uri, attachment.getSlug(), attachment.getTitle());
        this.file = file;
        this.description = attachment.getDescription();
    }

    public FileRepresentation getFile()
    {
        return file;
    }

    public void setFile(FileRepresentation file)
    {
        this.file = file;
    }

    public String getDescription()
    {
        return description;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static FileRepresentation buildFileRepresentation(Attachment attachment)
    {
        return new FileRepresentation(attachment, buildImageFileHref(attachment));
    }

    private static String buildAttachmentApiHref(Attachment attachment)
    {
        return "/api/1.0/attachment/" + attachment.getSlug();
    }

    private static String buildImageFileHref(Attachment attachment)
    {
        return "/attachment/" + attachment.getSlug() + "." + attachment.getExtension();
    }
}
