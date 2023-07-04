-- Notes contain the raw information that is formatted into a number of cards
-- according to the models
CREATE TABLE notes (
                       id              integer primary key,
    -- epoch milliseconds of when the note was created
                       guid            text not null,
    -- globally unique id, almost certainly used for syncing
                       mid             integer not null,
    -- model id
                       mod             integer not null,
    -- modification timestamp, epoch seconds
                       usn             integer not null,
    -- update sequence number: for finding diffs when syncing.
    --   See the description in the cards table for more info
                       tags            text not null,
    -- space-separated string of tags.
    --   includes space at the beginning and end, for LIKE "% tag %" queries
                       flds            text not null,
    -- the values of the fields in this note. separated by 0x1f (31) character.
                       sfld            integer not null,
    -- sort field: used for quick sorting and duplicate check. The sort field is an integer so that when users are sorting on a field that contains only numbers, they are sorted in numeric instead of lexical order. Text is stored in this integer field.
                       csum            integer not null,
    -- field checksum used for duplicate check.
    --   integer representation of first 8 digits of sha1 hash of the first field
                       flags           integer not null,
    -- unused
                       data            text not null
    -- unused
);