# Specifications

## Software components

### Data

Each project is store in a separated database as a file inside a folder with the same name

### Modes

Can switch between 11 modes

##### Project 

Create, load projects
Edit the current project informations

##### Song

Associate the sequences to song parts (16 song parts)
Play the succession of song parts, from a given one (with changing BPM)
Play one song part in loop

##### SequenceEdit

Make scores than play patterns (16 sequences)
Give a specific BPM to each sequence
Play the selected sequence in loop

##### PatternEdit

Make scores than play samples (16 patterns)
Give a specific time signature to each pattern
Play the selected pattern in loop (with the BPM of the selected sequence)

##### PatternLaunch

Play patterns

##### AudioSampler

Edit audio samples (16 tracks in each bank)
Load audio files as samples
Record samples
Route sample sound to audio mixer tracks
Play tracks (audio+video)

##### AudioMixer

Audio outputs for sampler tracks (16 tracks)
Edit volumes, effects send
Route sound to audio devices
Play tracks (audio+video)

##### AudioEffects

Edit audio effects (maximum of 16 effects - each effect as one instance)

##### VideoSampler

Associate videos to the audio tracks of the sampler (also consider JPEG, PNG and GIF as videos)
Play tracks (audio+video)

##### VideoMixer

Video outputs for video tracks (16 tracks)
Edit opacity, effects send
Route image to video devices
Play tracks (audio+video)

##### VideoEffects

Edit video effects (maximum of 16 effects - each effect as one instance)

### Items

Items are kind of entities stored in the database

Each item have a maximum of 16 parameters than can be edited with arrows
Can edit a parameter for several items at the same time (taking into account the value for the first selected item)

##### Project

Parameters: 

1. metronome_volume
2. precount => "based on selection" or a number

#### Scores

Every activated point in a pattern/sequence score is a pattern/sequence event

##### Song

##### SongPart

Parameters: 

1. sequence => the number of the sequence to play (1 to 16)
2. repetitions => how many time play the song part before playing to the next one

##### Sequence

Parameters: 

1. bpm
2. beats => for time signature
3. bars => for time signature
4. play_mode
5. repetitions

##### SequenceEvent

Parameters: 

1. action => "play" / "stop" / "inactive"
2. repetitions => "continue", or a number (used when a song part is playing)

##### Pattern

Parameters: 

1. steps => for time signature 
2. beats => for time signature
3. bars => for time signature
4. trigger_mode => "on step" / "on beat" / "on bar" (when to play the pattern when a pad is pressed in PatternLaunch mode)
5. playing_mode => "loop" / "simple"

##### PatternEvent

Parameter:

1. action => "play" / "inactive"
2. velocity => 0.00 to 1.00
3. detune => -1.00 to 1.00
4. transpose => -100 to 100
5. steps_length => how many time play the sample (when SamplerTrack's playing mode is "hold")
6. beats_length => how many time play the sample (when SamplerTrack's playing mode is "hold")

#### Tracks

##### AudioSamplerTrack

Parameters: 

1. volume
2. output_channel => routing the sound to the mixer
3. input_channel => routing the incoming sound for recording
4. choke => afford stopping some audio tracks when some others are played
5. voices => afford not stopping the previous sound when playing a sample again
6. playing_mode => "trigger" (play the full sample on pressing the corresponding pad) or "hold" (play the sample while keep pressed the corresponding pad)
7. start
8. length
9. attack
10. decay
11. sustain
12. release
13. transpose
14. detune
15. random_velocity
16. random_detune
17. random_transpose

##### AudioMixerTrack

Parameters: 

1. volume
2. output_channel => routing the sound audio devices
3. effects_send => how much of the sound is send to the audio effects (0.00 to 1.00)

##### VideoSamplerTrack

Parameters: 

1. output_channel => routing the image to video devices
2. effects_send => how much of the image is send to the video effects (0.00 to 1.00)
3. x_position
4. y_position
5. z_position
6. x_rotation
7. y_rotation
8. z_rotation
9. size
10. start
11. length => how many time to show the video (full reading by default, or 1 sec when it is an picture)
12. repetitions => show the video several times after the length is over
13. fadein
14. fadeout

##### VideoMixerTrack

Parameters: 

1. opacity
2. output_channel => routing the sound audio devices
3. effects_send => how much of the sound is send to the audio effects (0.00 to 1.00)

#### Audio effects

##### ReverbAudioEffect

Parameters: 

1. level
2. time
3. tone

##### EchoAudioEffect

Parameters: 

1. blend
2. delay
3. feedback

##### ChorusAudioEffect

Parameters: 

1. level
2. rate
3. depth

##### EqualizerAudioEffect

Parameters: 

1. low_frequency
2. medium_frequency
3. high_frequency

#### Video effects

**ColorVideoEffect**

Parameters: 

1. alpha
2. red
3. green
4. blue

##### **BlinkingVideoEffect**

Parameters: 

1. speed
2. intensity

##### **BlendingVideoEffect**

Parameters: 

1. mode => fusion between layers

##### **MovingVideoEffect**

Parameters: 

1. x_rate
2. y_rate
3. z_rate
4. speed
5. mode => "linear" / "easing"

##### **RotatingVideoEffect**

Parameters: 

1. x_rate
2. y_rate
3. z_rate
4. speed
5. mode => "linear" / "easing"



## Hardware components

### Inputs/Outputs

1 phone output + 1 to 8 audio outputs (Jack or XLR)
1 or 4 audio inputs (Jack or XLR)
1 to 4 video outputs (HDMI)

### Selectors

16 step potentiometers
(or a digit screen, with up and down buttons for each selector)

Selector.Mode
Selector.Sequence
Selector.Pattern
Selector.Page => moving through sequence/pattern scores
Selector.Track

### Padboard

Each pad represent a track, or a part of a score (depending on the current mode)

### Screen

Display file explorer or informations (project name, first selected item name, editing parameter)

### Switches

Switch.**Metronome** => (only in phone output)
Switch.**Precount** => play some metronome ticks before launching a score
Switch.**Record** => incoming sound will be recorded in the armed sampler track, replacing previous samples

### Buttons

Few buttons, with multiple labels for each
Drawn lines on the case to explain the different features

#### Arrows

Move through the file explorer or through item parameters

Button.Navigation.**Up**
Button.Navigation.**Down**
Button.Navigation.**Left**
Button.Navigation.**Right**

#### Player

Button.Player.**Forward**
Button.Player.**Backward**
Button.Player.**Pause**
Button.Player.**Play**

#### Banks

Button.Bank.**0** => "A"
Button.Bank.**1** => "B"
Button.Bank.**2** => "C"
Button.Bank.**3** => "D"

#### Action

Button.Action.**Load**: "load"
Button.Action.**New**: "new" / "insert" (followed by pads)
Button.Action.**Save**: "copy" (followed by Button.Load and pads)
Button.Action.**Select**: "select for editing" (followed by pads) / "enter" (after screen)
Button.Action.**Move**: "swap" (followed by pads) / "cancel" (after screen) / "stop" (followed by pads in Mode.SequenceEdit)



## Behavior

### Combinations

(order of the pressed buttons is important)

#### Global

Button.Select + pad => add the corresponding item to **selection**
bank button => change the **current bank**
Button.Pause => **pause** the playhead
Button.Pause + Button.Backward => **stop** all playing tracks, pause the playhead, restore the playhead at starting point

#### Mode.Project

Default selected item: current Project (depending on loaded project)
Selection type: none

pad => **play** a recorded song
Button.Load => open file explorer, **load** an existing project
Button.New => open file explorer, **create** new project with the given name, load it
Button.Save + Button.Load => open file explorer, **copy** current project with the given name, load it

#### Mode.Song

Default selected item: current Song (depending on loaded  project)
Selection type: SongPart

pad => **set starting** song part
Button.Select + pad => add the corresponding item to **selection**
Button.Play => **play** the song
Button.New + pad => **insert** a new part between two existing parts
Button.Move + 2 pads => **swap** part emplacements

#### Mode.SequenceEdit

Default selected item: current Sequence (depending on Selector.Sequence)
Selection type: SequenceEvent

Button.Play => **play** the current sequence
pad => **toggle** a SequenceEvent state between "play" and "unactive"
Button.Move + pad => **toggle** a SequenceEvent state between "stop" and "unactive"

#### Mode.PatternLaunch

Default selected item: current Sequence (depending on Selector.Sequence)
Selection type: Pattern

pad => **arm** pattern for playing
Button.Select + pad => add the corresponding item to **selection**
Button.Play => **play** the current sequence
Button.Move + 2 pads => **swap** pattern emplacements

#### Mode.PatternEdit

Default selected item: current Pattern (depending on Selector.Pattern)
Selection type: PatternEvent

Button.Play => **play** the current pattern
pad => **toggle** a PatternEvent state between "active" and "unactive"

#### Mode.AudioSampler

Default selected item: none
Selection type: AudioSamplerTrack

pad => **play** corresponding sampler track and video track
Button.Play => **play** the current pattern
Button.Load + pad => open file explorer, **load** **a sample** for a track
Button.New + pad => **arm** a track for recording
Button.Move + pad => add item to **swaping** list (effective on Button.Move release)
Button.Save + pad => add item to **copying ** list (parameters and audio file - effective on Button.Save release)
Button.Move + pad + bank => **swap** sampler tracks emplacements over banks
Button.Load + bank => open file explorer, **load an existing bank** of presets 
Button.New + bank => open file explorer, **create a bank** of presets
Button.Save + bank => open file explorer,  **overwrite an existing bank** of presets 

#### Mode.AudioMixer

Default selected item: none
Selection type: AudioMixerTrack

pad => **mute / unmute** mixer track
Button.Move + pad => add item to **swaping** list (effective on Button.Move release)
Button.Move + pad + bank => add item to **swaping** list (from another bank - effective on Button.Move release)
Button.Save + pad => add item to **copying** list (effective on Button.Save release)
Button.Load + bank => open file explorer, **load an existing bank** of presets 
Button.New + bank => open file explorer, **create a bank** of presets
Button.Save + bank => open file explorer,  **overwrite an existing bank** of presets 

#### Mode.VideoSampler

Default selected item: none
Selection type: VideoSamplerTrack

pad => **play** corresponding sampler track and video track
Button.Move + pad =>  add item to **swaping** list (effective on Button.Move release  - update output_channel parameter in sampler tracks)
Button.Save + pad  => add item to **copying** list (effective on Button.Save release)
Button.Move + pad + bank => **swap** video tracks emplacements over banks
Button.Load + bank => open file explorer, **load an existing bank** of presets 
Button.New + bank => open file explorer, **create a bank** of presets
Button.Save + bank => open file explorer,  **overwrite an existing bank** of presets

#### Mode.VideoMixer

Default selected item: none
Selection type: VideoMixerTrack

pad => **mute / unmute** mixer track
Button.Move + pad =>  add item to **swaping** list (effective on Button.Move release)
Button.Save + pad => add item to **copying** list (effective on Button.Save release)
Button.Move + pad + bank => **swap** mixer tracks emplacements over banks
Button.Load + bank => open file explorer, **load an existing bank** of presets 
Button.New + bank => open file explorer, **create a bank** of presets
Button.Save + bank => open file explorer,  **overwrite an existing bank** of presets 

#### Mode.AudioEffects

Default selected item: current AudioMixerTrack
Selection type: AudioEffect (one by one only, each effect corresponding to a pad having specific parameters)

pad => **activate / unactivate** the corresponding effect for the current mixer track

#### Mode.VideoEffects

Default selected item: current VideoMixerTrack
Selection type: VideoEffect (one by one only, each effect corresponding to a pad having specific parameters)

pad => **activate / unactivate** the corresponding effect for the current video track



### Reading scores

The song is divided in 16 **song parts**, played one after the other

**Time signatures** are made with the bar-beat-step format
	3 to 8 steps by beat (afford binary or ternary rhythm)
	1 to 8 beats by bar
	1 to 16 bars

A **playhead** is moving on once Button.Start is pressed
The playhead keeps its position when Button.Pause is pressed

All patterns played withing a sequence are stopped arriving at the end of the sequence (even when the sequence is repeated)